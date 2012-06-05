package com.fictionalrealm;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * User: dnavre
 * Date: 6/5/12
 * Time: 2:22 AM
 */
public class BadgeStore {
    private final Document doc;
    private final PdfWriter writer;
    
    public BadgeStore(String outputFile, Rectangle pageSize) throws BadgeGeneratorException {
        try {
            doc  = new Document(pageSize, 50, 50, 50, 50);
            writer = PdfWriter.getInstance(doc, new FileOutputStream(outputFile));
            doc.open();
        } catch (DocumentException e) {
            throw new BadgeGeneratorException("unexpected PDF document error");
        } catch (FileNotFoundException e) {
            throw new BadgeGeneratorException("Can not open file " + outputFile + " for writing");
        }
    }

    public void initializePages(ImportedPa) {

    }

    public void addBadge(Element e) throws BadgeGeneratorException {
        doc.newPage();

        try {
            doc.add(e);
            writer.flush();
        } catch (DocumentException e1) {
            throw new BadgeGeneratorException("Could not add badge to PDF document");
        }
    }

    public void close() {
        writer.flush();
        doc.close();
    }
}
