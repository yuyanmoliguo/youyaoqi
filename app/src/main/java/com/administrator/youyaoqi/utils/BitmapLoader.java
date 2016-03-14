package com.administrator.youyaoqi.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.bitmap.DiskCacheTool;
import com.administrator.youyaoqi.bitmap.MemoryCacheTool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 获取图片流程
 * 先在内部缓存中找，再到硬盘缓存上找，最后下载图片
 * Created by Administrator on 2016/3/8.
 */
public class BitmapLoader {

    static Handler mHandler = new Handler();
    private static ExecutorService es;

    /**
     * 建立线程池
     */
    static {
        es = Executors.newFixedThreadPool(3);
    }

    /**
     * 检索内部缓存
     * @param context
     * @param url
     * @param iv
     */
    public static void loaderImage(Context context,String url,ImageView iv){
        //设置默认图片
        iv.setBackgroundResource(R.mipmap.bg_default_cover);
        Bitmap cacheBitmap = MemoryCacheTool.getCache(url);
        //内部缓存没找到
        if (cacheBitmap==null){
            //初始化硬盘内存
            DiskCacheTool.init(context);
            //开启线程检索硬盘内存
            es.execute(new SearchBitmapThread(url,iv));
        //内部缓存找到
        }else {
            if (iv.getTag()!=null){
                if (iv.getTag().toString().equals(url)){
                    iv.setImageBitmap(cacheBitmap);
                }
            }
        }
    }

    /**
     * 异步线程检索硬盘内存图片
     */
    static class SearchBitmapThread implements Runnable{

        private String url;
        private ImageView iv;
        private Bitmap outCacheBitmap;

        public SearchBitmapThread(String url, ImageView iv) {
            this.url = url;
            this.iv = iv;
            iv.setBackgroundResource(R.mipmap.bg_default_cover);
        }

        /**
         * 检索硬盘内存的子线程
         */
        @Override
        public void run() {
            outCacheBitmap = DiskCacheTool.getCacheBitmap(url);
            //硬盘缓存没找到
            if (outCacheBitmap==null){
                //网络下载图片，并存入硬盘缓存和内部缓存
                outCacheBitmap = DownloadBitmap.downloadBitmap(url);
                DiskCacheTool.setDiskCache(url,outCacheBitmap);
            }else {
                //将图片数据存入内部缓存
                MemoryCacheTool.putCache(url, outCacheBitmap);
            }
            //将图片显示到控件上
            setBitmapToView(outCacheBitmap);
        }

        /**
         * 在主线程设置控件显示图片
         * @param bitmap
         */
        private void setBitmapToView(final Bitmap bitmap){
            if (mHandler!=null){
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (iv!=null&&iv.getTag()!=null){
//                            Log.i("ddd",iv.getTag().toString());
                            if (iv.getTag().toString().equals(url)){
                                iv.setImageBitmap(bitmap);
                            }
                        }
                    }
                });
            }
        }
    }

}
