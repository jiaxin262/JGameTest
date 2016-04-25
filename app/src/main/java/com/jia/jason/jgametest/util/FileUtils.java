package com.jia.jason.jgametest.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils
 */
public class FileUtils {

    //路径检查
    public static boolean checkFile(File file) throws IOException {
        boolean result = true;
        if (!file.exists()) {
            result = false;
            throw new IllegalArgumentException(file + "目录不存在！");
        }
        if (!file.isDirectory()) {
            result = false;
            throw new IllegalArgumentException(file + "不是目录！");
        }
        return result;
    }

    //获取给定目录下所有的文件
    public static ArrayList<File> reachFiles(File file, ArrayList<File> fileList) {
        try {
            if (checkFile(file)) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    //从网络读取文件
    public static List<String> readFileFromNet(String url) {
        if (url == null || url.length() == 0) {
            return null;
        }
        List<String> result = null;
        URL u = null;
        DataInputStream dis = null;
        try {
            u = new URL(url);
            dis = new DataInputStream(u.openStream());
            result = new ArrayList<String>();
            String s;
            while ((s = dis.readLine()) != null) {
                result.add(new String(s.getBytes("ISO-8859-1"), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
