package com.arpaul.utilitieslib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Aritra on 5/13/2016.
 */
public class FileUtils {

    /**
     * Saves inputstream into files.
     * @param inputStream
     * @param SdcardPath
     * @param fileName
     * @return
     */
    public static File saveInputStreamAsFile(InputStream inputStream, String SdcardPath, String fileName) {

        File themeFile = new File(SdcardPath);
        try {
            if(!themeFile.exists()) {
                new File(SdcardPath).mkdirs();
            }
            File file =new File(SdcardPath + fileName);
            if(file.exists()) {
                file.delete();
            }

            BufferedInputStream bis = new BufferedInputStream(inputStream);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte byt[] = new byte[1024];
            int noBytes;
            while((noBytes=bis.read(byt)) != -1)
                bos.write(byt,0,noBytes);
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return themeFile;
        }
    }

    /**
     * Copies file from one location to another.
     * @param fromFile
     * @param toFile
     * @throws IOException
     */
    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

    /**
     * Deletes all files in a directory
     * @param fileOrDirectory
     */
    public static void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

}
