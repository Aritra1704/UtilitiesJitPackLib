package com.arpaul.utilitieslib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Aritra on 5/13/2016.
 */
public class FileUtils {
    public static File SaveInputStreamAsFile(InputStream inputStream, String fileName, String SdcardPath) {

        File themeFile = new File(SdcardPath);
        try
        {
            if(!themeFile.exists())
            {
                new File(SdcardPath).mkdirs();
            }
            File file =new File(SdcardPath + fileName);
            if(file.exists())
            {
                file.delete();
            }

            BufferedInputStream bis = new BufferedInputStream(inputStream);
            FileOutputStream fos = new FileOutputStream(SdcardPath+fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte byt[] = new byte[1024];
            int noBytes;
            while((noBytes=bis.read(byt)) != -1)
                bos.write(byt,0,noBytes);
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            return themeFile;
        }
    }


}
