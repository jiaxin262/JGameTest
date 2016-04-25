package com.jia.jason.jgametest.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by jiaxin on 15-7-28.
 */
public class IOUtils {

    public static final String DEFAULT_CHARSET = "utf8";

    //查找给定目录下所有文件
    public static List<File> reachFiles(File file, List<File> fileList) throws IOException {
        if (file.exists()) {
            File[] files = file.listFiles();    //返回直接子目录（文件）的抽象
            if (files != null && files.length > 0) {    //如果file下有子目录
                for (File tempFile : files) {
                    if (tempFile.isDirectory()) { //若是目录
                        reachFiles(tempFile, fileList); //递归遍历
                    } else {  //若是文件
                        fileList.add(tempFile); //将其添加到结果列表中
                    }
                }
            }
        }
        return fileList;
    }



    //关闭流
    public static void closeIgnoreException(Closeable... closeable) {
        for (Closeable closeable1 : closeable) {
            if (closeable1 != null) {
                try {
                    closeable1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //向文件中写文件
    public static void writeLines(String fileName, List<String> lines) throws IOException {
        writeLines(fileName, lines, DEFAULT_CHARSET);
    }

    //向文件中写文件，指定charset
    public static void writeLines(String fileName, List<String> lines, String charset)
            throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(fileName);
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file), charset));
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } finally {
            IOUtils.closeIgnoreException(bufferedWriter);
        }
    }
}
