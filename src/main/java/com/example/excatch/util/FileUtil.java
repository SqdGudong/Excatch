package com.example.excatch.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author songqd
 * @Date 2019/9/5
 * @Description
 */
public class FileUtil {
    /**
     * @description 创建文件
     * @parm [path]
     * @author songqd
     * @date 2019/9/5
     * @reutun java.lang.String
     * @modifier
     */
    public static String createFile(String path) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String filenameTemp = path + date + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            try {
                filename.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filenameTemp;
    }

    /**
     * @description 文件信息写入
     * @parm [file, conent]
     * @author songqd
     * @date 2019/9/5
     * @reutun void
     * @modifier
     */
    public static void writeFile(String filePath, String conent) {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true)));
            out.write(conent + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @description 按行读取文件信息
     * @parm [path]
     * @author songqd
     * @date 2019/9/5
     * @reutun java.lang.String
     * @modifier
     */
    public static String readFile(String path) {
        StringBuilder result = new StringBuilder();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result.toString();
    }

    /**
     * @description 判断字段是否在与文件某一行文本对应
     * @parm [excDescribe, path]
     * @author songqd
     * @date 2019/9/5
     * @reutun boolean
     * @modifier
     */
    public static boolean isInFile(String excDescribe, String filePath) {
        boolean flag = false;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(excDescribe)){
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return flag;
    }
}
