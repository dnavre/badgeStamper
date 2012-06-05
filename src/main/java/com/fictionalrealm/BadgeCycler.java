package com.fictionalrealm;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * User: dnavre
 * Date: 6/5/12
 * Time: 12:23 AM
 */
public class BadgeCycler {
    
    private final PersonListReader reader;
    private final BadgeStamper stamper;
    private final BadgeStore store;
    
    public BadgeCycler(String inputPdf, String inputCsv, String outputPdf) throws BadgeGeneratorException {
        reader = initCsv(inputCsv);
        stamper = initStamper(inputPdf);
        store = initBadgeStore(outputPdf);
    }

    private BadgeStamper initStamper(String inputPdf) throws BadgeGeneratorException {
        return new BadgeStamper(inputPdf);
    }

    private BadgeStore initBadgeStore(String outputPdf) throws BadgeGeneratorException {
        return new BadgeStore(outputPdf, stamper.getPageSize());
    }

    private PersonListReader initCsv(String inputCsv) throws BadgeGeneratorException {
        try {
            PersonListReader r = new PersonListReader(inputCsv);
            return r;
        } catch (FileNotFoundException e) {
            throw new BadgeGeneratorException("CSV file " + inputCsv + " was not found");
        } catch (IOException e) {
            throw new BadgeGeneratorException("Error parsing CSV file " + inputCsv);
        }
    }



    public void cycle() throws BadgeGeneratorException {
        for (PersonRecord pr: reader) {
            store.addBadge(new Paragraph("asdasd"));
        }

        store.close();
    }
}
