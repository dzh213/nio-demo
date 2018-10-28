package com.nio.demo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChanneDemo {
    public static void main(String[] args) {
//        readForBIO();
        readFOrNIO();
    }

    private static void readFOrNIO() {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("pom.xml","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int index = -1;
            while ((index = fileChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());

                }
                byteBuffer.compact();
                index = fileChannel.read(byteBuffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void readForBIO() {
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream("pom.xml"));
            byte[] buff = new byte[1024];
            int index = -1;
            while ((index = bufferedInputStream.read(buff,0,buff.length)) != -1) {
                String text = new String(buff,0,index,"utf-8");
                System.out.println(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
