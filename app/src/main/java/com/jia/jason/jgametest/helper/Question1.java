package com.jia.jason.jgametest.helper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Question1 {

    private final String CONTENT_0 = "getScreenHeight";
    private final String CONTENT_1 = "getScreenWidth";
    private final String CONTENT_2 = "getDisplayMetrics";

    private File srcFile = new File("D:/m_adr_atom_flight/SRC/app/src/main/java/com/mqunar/atom/flight");
    private File targetFile = new File("E:/width_height_used_list.text");

    private HashMap<String, List> resultFileAndLine = new HashMap<String, List>();
    private int numberTotalCount;
    private int classTotalCount;

    public static void main(String[] args) {
        new Question1().countChars(new File("D:/m_adr_atom_flight/SRC/app/src/main/java/com/mqunar/atom/flight"));
    }


    public void countChars(File file) {
        try {
            if (checkFile(file)) {
                ArrayList<File> fileList = new ArrayList<File>();
                fileList = reachFiles(file, fileList);
                for (File tempFile : fileList) {
                    if (!tempFile.getName().contains("FlightResUtils")) {
                        findTargetFile(tempFile);
                    }
                }
                printResult(targetFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + "file not found");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + "file is not a directory");
        }
        return true;
    }

    public ArrayList<File> reachFiles(File file, ArrayList<File> fileList) {
        try {
            if (checkFile(file)) {
                File[] files = file.listFiles();
                if (files != null && files.length > 0) {
                    for (File tempFile : files) {
                        if (tempFile.isDirectory()) {
                            reachFiles(tempFile, fileList);
                        } else {
                            fileList.add(tempFile);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public void findTargetFile(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file)
                    )
            );
            String line;
            int lineNumber = 1;
            List<Integer> lineNumbers = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
                if (checkLine(line)) {
                    lineNumbers.add(lineNumber);
                    numberTotalCount++;
                }
                lineNumber++;
            }
            if (lineNumbers.size() != 0) {
                classTotalCount++;
                resultFileAndLine.put(file.getName(), lineNumbers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean checkLine(String line) {
        return !line.contains("import") && line.contains(CONTENT_0) ||
                line.contains(CONTENT_1) || line.contains(CONTENT_2);
    }

    public void printResult(File destFile) {
        PrintWriter pt = null;
        try {
            pt = new PrintWriter(destFile);
            pt.println("classTotalCount:"+classTotalCount);
            pt.println("numberTotalCount:"+numberTotalCount);
            pt.println();
            Set<String> set = resultFileAndLine.keySet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String key = it.next();
                pt.println(key + ":    lineNumbers" + resultFileAndLine.get(key).toString());
                pt.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            pt.flush();
            pt.close();
        }
    }
}
