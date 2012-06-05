package com.fictionalrealm;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * User: dnavre
 * Date: 6/5/12
 * Time: 12:23 AM
 */
public class PersonListReader implements Iterable<PersonRecord> {

    private final CSVReader reader;
    private final List<String[]> data;

    public PersonListReader(String file) throws IOException {
        reader = new CSVReader(new FileReader(file));
        data = reader.readAll();
    }


    @Override
    public Iterator<PersonRecord> iterator() {
        return new Iterator<PersonRecord>() {
            private final Iterator<String[]> internalIterator = data.iterator();

            @Override
            public boolean hasNext() {
                return internalIterator.hasNext();
            }

            @Override
            public PersonRecord next() {
                String[] record = internalIterator.next();
                PersonRecord pr = new PersonRecord();
                pr.setName(record[1]);
                pr.setEmail(record[2]);
                pr.setOccupation(record[3]);
                pr.setOrganization(record[4]);
                pr.setTwitter(record[5]);

                return pr;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
