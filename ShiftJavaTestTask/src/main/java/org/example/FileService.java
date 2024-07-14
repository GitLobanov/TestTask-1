package org.example;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public  FileService () {
    }

    public List<Object> ReadFilesGetLines(List <String> paths) {
        List<Object> lines = new ArrayList();
        List<FileReader> fileReaders = paths.stream()
                .map(fileName -> {
                    try {
                        return new FileReader(fileName);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .toList();

        for (int i = 0; i < fileReaders.size(); i++) {
            BufferedReader reader = new BufferedReader(fileReaders.get(i));
            String line = null;
            try {
                line = reader.readLine();
                lines.add(line);
                while (line != null) {
                    line = reader.readLine();
                    if (line != null) lines.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return  lines;

    }

    public List DifferLinesToLists (List<Object> lines) {
        List listStrings = new ArrayList();
        List listIntegers = new ArrayList();
        List listFloats = new ArrayList();

        for (Object line : lines) {
            try {
                if (isInteger(line.toString())) {
                    listIntegers.add(Integer.parseInt(line.toString()));
                }
                else if (isFLoat(line.toString())) {
                    listFloats.add(Float.parseFloat(line.toString()));
                }
                else {
                    listStrings.add(line);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return  List.of(listStrings,listIntegers,listFloats);
    }

    public static boolean isInteger(String str) {
        try {
            BigInteger bgi = new BigInteger((str));
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isFLoat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void CreateFiles(List <List> listData, String preffixFiles, String path) {
        List <String> listStrings = listData.get(0);
        List  listIntegers = listData.get(1);
        List <Float> listFloats = listData.get(2);

        try
        {
            String naming = (!preffixFiles.isEmpty()) ? preffixFiles : "";

            if (!path.isEmpty()) {
                naming = path + "/" + naming;
                Path p = Paths.get(path);
                Files.createDirectories(p);
            }

            // load data for string
            if (!listStrings.isEmpty()) {
                File fString = new File(naming + "strings.txt");
                FileWriter writerString = new FileWriter(fString, false);
                for (Object ob : listStrings) {
                    writerString.append(ob.toString());
                    writerString.append('\n');
                }
                writerString.flush();
            }

            // load data for integer
            if (!listIntegers.isEmpty()) {
                File fInt = new File(naming + "integers.txt");
                FileWriter writerInt = new FileWriter(fInt, false);
                for (Object ob : listIntegers) {
                    writerInt.append(ob.toString());
                    writerInt.append('\n');
                }

                writerInt.flush();
            }


            // load data for floats
            if (!listFloats.isEmpty()) {
                File fFloat = new File(naming + "floats.txt");
                FileWriter writerFloat = new FileWriter(fFloat, false);
                for (Object ob : listFloats) {
                    writerFloat.append(ob.toString());
                    writerFloat.append('\n');
                }
                writerFloat.flush();
            }


        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    public boolean [] UpdateFiles(List <List> listData, String preffixFiles, String path) {
        List <String> listStrings = listData.get(0);
        List  listIntegers = listData.get(1);
        List <Float> listFloats = listData.get(1);
        boolean appendStrings = true;
        boolean appendInteger = true;
        boolean appendFloat = true;

        try
        {
            String naming = (!preffixFiles.isEmpty()) ? preffixFiles : "";
            if (!path.isEmpty()) {
                naming = path + "/" + naming;
                Path p = Paths.get(path);
                Files.createDirectories(p);
            }

            File fString = new File(naming + "strings.txt");
            File fInt = new File(naming + "integers.txt");
            File fFloat = new File(naming + "floats.txt");

            if (fString.createNewFile()){
                System.out.println("Named file: " + fString.getName() + " - doesn't exist for append");
                fString.delete();
                appendStrings = false;
            } else {
                FileWriter writerString = new FileWriter(naming + "strings.txt", true);
                for (Object ob : listStrings) {
                    writerString.append(ob.toString());
                    writerString.append('\n');
                }

                writerString.flush();
            }

            if (fInt.createNewFile()){
                System.out.println("Named file: " + fInt.getName() + " - doesn't exist for append");
                fInt.delete();
                appendInteger = false;
            } else {
                FileWriter writerInt = new FileWriter(naming + "integers.txt", true);
                for (Object ob : listIntegers) {
                    writerInt.append(ob.toString());
                    writerInt.append('\n');
                }

                writerInt.flush();
            }

            if (fFloat.createNewFile()){
                System.out.println("Named file: " + fFloat.getName() + " - doesn't exist for append");
                fFloat.delete();
                appendFloat = false;
            } else {
                FileWriter writerFloat = new FileWriter(naming + "floats.txt", true);
                for (Object ob : listFloats) {
                    writerFloat.append(ob.toString());
                    writerFloat.append('\n');
                }

                writerFloat.flush();
            }

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return new boolean[]{appendStrings,appendInteger,appendFloat};
    }



}
