package edu.hit.core.io;

import org.junit.Test;

import java.io.*;

/**
 * <p>Transform</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年5月11日 - 下午7:30:50
 */
public class Transform {

    @Test
    public void test_01_outputStreamWriter() {
        String url = "e:/test/java/chap09/file.txt";
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(url, true);
            osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write("灵道猿人");
            osw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (osw != null) osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_02_inputStreamWriter() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        try {
            str = br.readLine();
            while (str != null) {
                System.out.println("---->" + str.toUpperCase());
                if (str.equalsIgnoreCase("exit")) break;
                str = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
