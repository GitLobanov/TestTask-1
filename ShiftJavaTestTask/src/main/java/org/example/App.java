package org.example;


import java.util.*;

public class App
{

    private static FileService fileService = new FileService();
    private static ReportToDataFromFiles reportToDataFromFiles  = new ReportToDataFromFiles();

    public static void main( String[] args ) {
        Map<String, Boolean> cmd = new HashMap<>();
        cmd.put("-s", false);
        cmd.put("-a", false);
        cmd.put("-p", false);
        cmd.put("-o", false);
        cmd.put("-f", false);
        String preffixFiles = "";
        String pathFiles = "";
        boolean appendStrings = true;
        boolean appendInteger = true;
        boolean appendFloat = true;

        String [] cap = {"-s", "-o", "/some/files", "-p", "sample-", "in1.txt", "in2.txt"};
        args = cap;

        List <String> nameFiles = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (cmd.containsKey(args[i])) {
                cmd.put(args[i], true);
            } else if (args[i].indexOf(".txt") > 0) {
                nameFiles.add(args[i]);
            }

            if (args[i].equals("-p")) preffixFiles = args[i+1];
            if (args[i].equals("-o"))  pathFiles = "." + args[i+1];
        }

        List<Object> listLines = fileService.ReadFilesGetLines(nameFiles);
        List list = fileService.DifferLinesToLists(listLines);


        // create or update files
        if (cmd.get("-a")) {
            boolean [] append = fileService.UpdateFiles(list, preffixFiles, pathFiles);
            appendStrings = append[0];
            appendInteger = append[1];
            appendFloat = append[2];
        } else if (!cmd.get("-a")) {
            fileService.CreateFiles(list, preffixFiles, pathFiles);
        }

        // statistics
        if (cmd.get("-s")) {
            reportToDataFromFiles.GetShortReport(list, appendStrings,appendInteger,appendFloat);
        } else if (cmd.get("-f")) {
            reportToDataFromFiles.GetFullReport(list, appendStrings,appendInteger,appendFloat);
        }

    }
}
