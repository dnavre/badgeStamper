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
    private final PdfTemplate badgeTemplate;
    private final PdfContentByte cb;

    private final BaseFont bFont;

    public BadgeStamper(String inputPdf, String outputFile) throws BadgeGeneratorException {

        try {
            bFont = initFont();
        }  catch (DocumentException e) {
            throw new BadgeGeneratorException("Error while loading fonts", e);
        } catch (IOException e) {
            throw new BadgeGeneratorException("Error while loading fonts", e);
        }

        try {

            pdf = new PdfReader(new FileInputStream(inputPdf));

            if(pdf.getNumberOfPages() == 0) {
                throw new BadgeGeneratorException("no pages detected in supplied sample PDF");
            }

            doc  = new Document(pdf.getPageSize(1), 0, 0, 0, 0);
            writer = PdfWriter.getInstance(doc, new FileOutputStream(outputFile));
            doc.open();
            cb = writer.getDirectContent();

            badgeTemplate = getTemplate();

        } catch (FileNotFoundException e) {
            throw new BadgeGeneratorException("Can not open file " + outputFile + " for writing");
        } catch (IOException e) {
            throw new BadgeGeneratorException("Error reading PDF sample file " + inputPdf);
        } catch (DocumentException e) {
            throw new BadgeGeneratorException("Unexpected PDF document error", e);
        }
    }

    private BaseFont initFont() throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont("c:/windows/fonts/arianamu.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        
        return bf;
    }
    
    private PdfTemplate getTemplate() throws DocumentException {

        PdfImportedPage p = writer.getImportedPage(pdf, 1);

        PdfTemplate badgeTemplate = PdfTemplate.createTemplate(writer, 0, 0);
        badgeTemplate.addTemplate(p, 0, 0);

        return badgeTemplate;
    }

    private PdfPTable generateTable() {
        return new PdfPTable(2);
    }

    public void stamp(PersonRecord pr) throws DocumentException {
        doc.newPage();
        
        cb.addTemplate(writer.getImportedPage(pdf, 1), 0, 0);

        cb.beginText();
        cb.setFontAndSize(bFont, 22);
        cb.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, pr.getName(), 45, 170, 0);
        cb.setFontAndSize(bFont, 11);
        cb.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, pr.getEmail(), 55, 154, 0);
        cb.showTextAlignedKerned(PdfContentByte.ALIGN_RIGHT, pr.getOrganization(), 220, 60, 0);
        cb.endText();

        writer.flush();
    }

    public void close() {
        writer.flush();
        doc.close();
    }
}
