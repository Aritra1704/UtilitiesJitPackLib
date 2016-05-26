package com.arpaul.utilitieslib;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aritra on 5/13/2016.
 */
public class BitmapUtils {

    public static Drawable getDrawbleFromAsset(Context context, String assetPath){
        AssetManager manager = context.getAssets();
        Drawable drawable = null;
        try {
            InputStream open = manager.open(assetPath);
            Bitmap bitmap = BitmapFactory.decodeStream(open);

            /*File file = FileUtils.SaveInputStreamAsFile(open,"test.png","usn");
            Bitmap bitmap = decodeSampledBitmapFromResource(file,100,100);*/

            drawable = new BitmapDrawable(context.getResources(), bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return drawable;
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(File f, int reqWidth, int reqHeight) {
        try {
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(new FileInputStream(f), null, options);

            // Calculate inSampleSize
            options.inSampleSize = 1;

            // Decode bitmap with inSampleSize set

            options.inJustDecodeBounds = false;
            Bitmap tmpBitmap = BitmapFactory.decodeStream(
                    new FileInputStream(f), null, options);
            tmpBitmap = getResizedBitmap(tmpBitmap, reqWidth, reqHeight);
            float rotation = rotationForImage(null, Uri.fromFile(f));
            if (rotation != 0f) {
                Matrix matrix = new Matrix();
                matrix.preRotate(rotation);
                tmpBitmap = Bitmap.createBitmap(tmpBitmap, 0, 0,
                        tmpBitmap.getWidth(), tmpBitmap.getHeight(), matrix,
                        true);
            }
            return tmpBitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getResizedBitmap(Bitmap bitmap, float width, float height)
    {
        if (bitmap != null) {

        }
        float bmpHieght = bitmap.getHeight();
        float bmpWidth = bitmap.getWidth();

        Bitmap scaledBitmap = null;
        if (bmpHieght < height && bmpWidth < width) {
            return bitmap;
        }

        int scaledWidth = 0;
        int scaledHeight = 0;

        if (bmpWidth / width < bmpHieght / height) {
            scaledWidth = convertPixelToDp((int) (bmpWidth * height / bmpHieght));
            scaledHeight = convertPixelToDp((int) height);
            scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth,
                    scaledHeight, true);
        } else {
            scaledWidth = convertPixelToDp((int) width);
            scaledHeight = convertPixelToDp((int) (bmpHieght * width / bmpWidth));
            scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth,
                    scaledHeight, true);
        }
        return scaledBitmap;
    }

    public static float rotationForImage(Context context, Uri uri) {
        try {
            if (context != null && uri.getScheme().equals("content")) {
                String[] projection = { MediaStore.Images.ImageColumns.ORIENTATION };
                Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
                if (c.moveToFirst()) {
                    return c.getInt(0);
                }
            } else if (uri.getScheme().equals("file")) {
                try {
                    ExifInterface exif = new ExifInterface(uri.getPath());
                    int rotation = (int) exifOrientationToDegrees(exif
                            .getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                    ExifInterface.ORIENTATION_NORMAL));
                    return rotation;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0f;
    }

    public static int convertPixelToDp(int px) {
        return (int) (px * (160 / 160f));
    }
    private static float exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
}
