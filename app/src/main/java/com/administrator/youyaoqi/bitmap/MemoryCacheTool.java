package com.administrator.youyaoqi.bitmap;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 *  内部缓存类
 * Created by Administrator on 2016/3/8.
 */
public class MemoryCacheTool {

    private static LruCache<String,Bitmap> lc;

    /**
     * 设置缓存大小
     */
    static {
        //1024*1024*4
        int cacheSize = (int)Runtime.getRuntime().maxMemory()/8;
        lc = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回Bitmap对象的大小
                return super.sizeOf(key, value);
            }
        };
    }

    /**
     * 把图片存入缓存
     * @param url
     * @param bitmap
     */
    public static void putCache(String url,Bitmap bitmap){
        lc.put(url,bitmap);
    }

    /**
     * 从缓存中拿出图片
     * @param url
     * @return
     */
    public static Bitmap getCache(String url){
        return lc.get(url);
    }

}
