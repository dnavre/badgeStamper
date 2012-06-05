package com.fictionalrealm;

import org.apache.commons.cli.*;
import org.slf4j.LoggerFactory;

/**
 * User: dnavre
 * Date: 6/5/12
 * Time: 12:07 AM
 */
public class Starter {
    
    private static final String mainLogger = "MAIN";
    
    private final Options options = new Options();
    
    private Starter() {
        initOptions();
    }

    private void initOptions() {
        options.addOption("i", true, "Input PDF file to be stamped");
        options.addOption("d", true, "CSV file with the list of stamps");
        options.addOption("o", false, "Output PDF. defaults to output.pdf");
    }

    private void parseOptions(String args[]) {
        CommandLineParser parser = new PosixParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            BadgeCycler bc = new BadgeCycler(cmd.getOptionValue("i"), cmd.getOptionValue("d"), cmd.getOptionValue("o", "output.pdf"));
            bc.cycle();
        } catch (ParseException e) {
            LoggerFactory.getLogger(mainLogger).error("Could not parse arguments");
            // TODO print options
        } catch (BadgeGeneratorException e) {
            LoggerFactory.getLogger(mainLogger).error(e.getMessage(), e);
        }
    }
    
    public static void main(String args[]) {
        new Starter().parseOptions(args);
    }
}
