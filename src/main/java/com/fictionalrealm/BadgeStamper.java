package com.fictionalrealm;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * User: dnavre
 * Date: 6/5/12
 * Time: 12:23 AM
 */
public class BadgeStamper {

    private PdfReader pdf;
    private PdfImportedPage samplePage;

    public BadgeStamper(String inputPdf) throws BadgeGeneratorException {
        try {
            pdf = new PdfReader(new FileInputStream(inputPdf));

            if(pdf.getNumberOfPages() == 0) {
                throw new BadgeGeneratorException("no pages detected in supplied sample PDF");
            }


            samplePage = pdf.get
        } catch (IOException e) {
            throw new BadgeGeneratorException("Error reading PDF sample file " + inputPdf);
        }
    }

    private void importSample()

    public Element stamp(PersonRecord pr) {

        PdfCopy copy = new PdfCopy(pdf.)

        PdfCopy.PageStamp stamp;
        stamp
    }

    public Rectangle getPageSize() {
        return pdf.getPageSize(1);
    }
}
