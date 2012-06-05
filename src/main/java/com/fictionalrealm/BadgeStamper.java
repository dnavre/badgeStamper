package com.fictionalrealm;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: dnavre
 * Date: 6/5/12
 * Time: 12:23 AM
 */
public class BadgeStamper {
    
    private static final int NUMBER_OF_CARDS_PER_PAGE = 6;

    private final PdfReader pdf;
    private final Document doc;
    private final PdfWriter writer;
    private int cardOnPage = 0;
    private PdfPTable table;
    private final PdfTemplate badgeTemplate;

    public BadgeStamper(String inputPdf, String outputFile) throws BadgeGeneratorException {
        try {
            pdf = new PdfReader(new FileInputStream(inputPdf));

            if(pdf.getNumberOfPages() == 0) {
                throw new BadgeGeneratorException("no pages detected in supplied sample PDF");
            }

            doc  = new Document(PageSize.A4, 0, 0, 0, 0);
            writer = PdfWriter.getInstance(doc, new FileOutputStream(outputFile));
            doc.open();

            badgeTemplate = getTemplate();

            doc.newPage();
            table = generateTable();

        } catch (FileNotFoundException e) {
            throw new BadgeGeneratorException("Can not open file " + outputFile + " for writing");
        } catch (IOException e) {
            throw new BadgeGeneratorException("Error reading PDF sample file " + inputPdf);
        } catch (DocumentException e) {
            throw new BadgeGeneratorException("unexpected PDF document error", e);
        }
    }
    
    private PdfTemplate getTemplate() throws DocumentException {

        PdfImportedPage p = writer.getImportedPage(pdf, 1);
        Image img = Image.getInstance(p);
        img.setAbsolutePosition(1, 1);

        PdfTemplate badgeTemplate = PdfTemplate.createTemplate(writer, 0, 0);
        badgeTemplate.addImage(img);

        return badgeTemplate;
    }

    private PdfPTable generateTable() {
        return new PdfPTable(2);
    }

    public void stamp(PersonRecord pr) throws DocumentException {

        if(cardOnPage++ >= NUMBER_OF_CARDS_PER_PAGE) {
            doc.add(table);
            writer.flush();

            doc.newPage();
            cardOnPage = 0;
            table = generateTable();
        }

        PdfContentByte c = new PdfContentByte ();

        Image img = Image.getInstance(badgeTemplate);
        img.setAbsolutePosition(1,1);

        table.addCell(c);
    }

    public void close() {
        writer.flush();
        doc.close();
    }
}
