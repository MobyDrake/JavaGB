package lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    public static void main(String[] args) {

    }

    //первое задание
    public static void readInArray(File file) {
        byte[] arr;
        try(FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream outByte = new ByteArrayOutputStream()) {
            int count;
            while((count = in.read()) != -1) {
                outByte.write(count);
            }
            arr = outByte.toByteArray();
            for(byte b : arr) {
                System.out.print(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //второе задание
    public static void sewFiles(File outFile, File...files) {
        ArrayList<InputStream> list = new ArrayList<>();
        for(File file : files) {
            try {
                list.add(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try(SequenceInputStream in = new SequenceInputStream(Collections.enumeration(list));
            FileOutputStream out = new FileOutputStream(outFile)) {

            int x;
            while((x = in.read()) != -1) {
                out.write(x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
