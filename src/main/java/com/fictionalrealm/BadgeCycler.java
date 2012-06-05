package com.fictionalrealm;

import com.lowagie.text.DocumentException;

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
    
    public BadgeCycler(String inputPdf, String inputCsv, String outputPdf) throws BadgeGeneratorException {
        reader = initCsv(inputCsv);
        stamper = initStamper(inputPdf, outputPdf);
    }

    private BadgeStamper initStamper(String inputPdf, String outputPdf) throws BadgeGeneratorException {
        return new BadgeStamper(inputPdf, outputPdf);
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
            try {
                stamper.stamp(pr);
            } catch (DocumentException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        stamper.close();
    }
}
