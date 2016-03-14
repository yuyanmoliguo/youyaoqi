package com.administrator.youyaoqi.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/8.
 */
public class DownloadBitmap {
    /**
     * 网络下载图片
     */
    public static Bitmap downloadBitmap(String url) {
        InputStream is = null;
        HttpURLConnection huc = null;
        try {
            URL urlparam = new URL(url);
            huc = (HttpURLConnection) urlparam.openConnection();
            huc.connect();
            is = huc.getInputStream();
            int len =0;
            byte[] buff = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(buff))!=-1){
                baos.write(buff,0,len);
            }
            is.close();
            //将图片数据转换为字节数组
            byte[] tobuff = baos.toByteArray();
//            //新建options
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            //不会真正的加载bitmap对象到内存中，但是会返回图片的高度和宽度信息。
//            options.inJustDecodeBounds = true;
//            //制作图片
//            BitmapFactory.decodeByteArray(tobuff,0,tobuff.length,options);
//            int outWidth = options.outWidth;
//            int outHeight = options.outHeight;
//            //计算图片压缩比例
//            int radio1 = outHeight/200;
//            int radio2 = outWidth/200;
//            //取长宽最大值
//            int radio = Math.max(radio1,radio2);
//            if (radio<1){
//                options.inSampleSize = 1;
//            }else{
//                options.inSampleSize = radio;
//            }
//            //将获取Bitmap对象的时候，将inJustDecodeBounds重新设置为true
//            options.inJustDecodeBounds = false;
//            return BitmapFactory.decodeByteArray(tobuff,0,tobuff.length,options);
            return BitmapFactory.decodeByteArray(tobuff,0,tobuff.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (huc!=null){
                huc.disconnect();
            }
        }
        return null;
    }
}
