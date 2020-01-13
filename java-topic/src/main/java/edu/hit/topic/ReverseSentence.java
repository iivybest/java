package edu.hit.topic;

import java.util.Scanner;

/**
 * <p>_002_ReverseSentence</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2015年6月12日 - 上午9:14:24
 * @versino 1.0
 */
public class ReverseSentence {
    private static boolean stop = false;

    public static void main(String[] args) {
        Scanner scanner;
        while (!stop) {
            System.out.println("please input sentence:");
            scanner = new Scanner(System.in);
            String original = scanner.nextLine();
            String result = reverseWord(original);
            System.out.println(result);
            if ("exit".equals(original)) {
                stop = true;
            }
        }


    }

    public static char[] reverseWordsOrder(char[] chars) {
        int start = -1;
        int end = 0;
        //No.1
        //开始写代码，将句子倒序输出，但单词本身不变

        int len = chars.length;
        char[] temp = new char[len];
        end = len - 1;
        start = len - 1;
        int j, idx = 0;
        while (start >= 0) {
            char c = chars[start];
            if ((!(c <= '9' && c >= '0')
                    && !((c <= 'Z' && c >= 'A') || c >= 'a' && c <= 'z'))) {
                if (start <= end) {
                    for (j = start + 1; j <= end; j++) {
                        temp[idx++] = chars[j];
                    }
                    temp[idx++] = chars[start];
                }
                end = start - 1;
            }
            start--;
        }
        for (int t = 0; t <= end; t++) {
            temp[idx++] = chars[t];
        }

        chars = temp;


        //end_code
        return chars;
    }

    public static String reverseWord(String original) {
        String result = null;
//		char[] chars = original.toCharArray();
//		int len = chars.length;
//		char[] alpha = new char[len];
//		int end = len - 1, j, idx = 0, i = len - 1;
//		while(i >= 0) {
//			if((! isNum(chars[i]) && ! isLetter(chars[i])) || i == 0) {
//				if(i <= end && i > 0 ){
//					for(j = i + 1; j <= end; j ++){
//						alpha[idx++] = chars[j];
//					}
//					alpha[idx++] = chars[i];
//				}else if(i == 0) {
//					for(j = i; j <= end; j ++){
//						alpha[idx++] = chars[j];
//					}
//				}
//				end = i - 1;
//			}
//			i --;
//		}
//		result = String.valueOf(alpha);

        result = String.valueOf(reverseWordsOrder(original.toCharArray()));
        return result;
    }


    private static boolean isNum(char c) {
        return (c >= '0' && c <= '9');
    }

    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }


    public static void reverse(char[] chars) {
        reverse(chars, 0, chars.length);
    }

    public static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start, end);
            start++;
            end--;
        }
    }


    /*
     *  数组换位运算
     */

    public static <T> void swap(T[] t, int i, int j) {
        T temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(char[] t, int i, int j) {
        char temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(byte[] t, int i, int j) {
        byte temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(short[] t, int i, int j) {
        short temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(int[] t, int i, int j) {
        int temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(long[] t, int i, int j) {
        long temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(float[] t, int i, int j) {
        float temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(double[] t, int i, int j) {
        double temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    public static void swap(boolean[] t, int i, int j) {
        boolean temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

}
