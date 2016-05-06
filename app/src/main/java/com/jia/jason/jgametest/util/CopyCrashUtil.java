package com.jia.jason.jgametest.util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by xin.jia
 * since 2016/5/6
 */
public class CopyCrashUtil {

    private static File targetFile;
    private static ArrayList<String> lineList = new ArrayList<String>();
    private static String fileName = getSDPath() +"/" + "crash.text";

    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    public static boolean startCopy(File file) {
        try {
            if (checkFile(file)) {
                ArrayList<File> fileList = new ArrayList<File>();
                fileList = reachFiles(file, fileList);
                for (File tempFile : fileList) {
                    findTargetFile(tempFile);
                }
                targetFile = new File(fileName);
                printResult(targetFile);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + "file not found");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + "file is not a directory");
        }
        return true;
    }

    public static ArrayList<File> reachFiles(File file, ArrayList<File> fileList) {
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

    public static void findTargetFile(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file)
                    )
            );
            String line;
            while ((line = br.readLine()) != null) {
                lineList.add(line);
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

    public static void printResult(File destFile) {
        PrintWriter pt = null;
        try {
            pt = new PrintWriter(destFile);
            pt.println();
                for (String line : lineList) {
                    pt.println(line);
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            pt.flush();
            pt.close();
        }
    }

}
