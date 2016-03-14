package com.administrator.youyaoqi.bitmap;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 硬盘缓存类
 * Created by Administrator on 2016/3/8.
 */
public class DiskCacheTool {

    private static DiskLruCache dlc;

    /**
     * 硬盘初始化
     * @param context
     */
    public static void init(Context context){
        /**
         * Opens the cache in {@code directory}, creating a cache if none exists
         * there.
         *
         * @param directory a writable directory  缓存目录
         * @param versionCode 版本号
         * @param valueCount the number of values per cache entry. Must be positive. 1=1 默认1
         * @param maxSize the maximum number of bytes this cache should use to store  用来缓存的空间大小
         * @throws IOException if reading or writing the cache directory fails
         */
        try {
            dlc = dlc.open(getCacheDirectory(context),getVersonCode(context),1,4*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置缓存中的图片名，并用MD5方法加密
     * @param url
     * @return
     */
    private static String getCacheKey(String url){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(url.getBytes());
            byte[] bytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<bytes.length;i++){
                sb.append(Integer.toHexString(Math.abs(bytes[i])));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.valueOf(url.hashCode());
    }

    /**
     * 将图片存入硬盘缓存
     * url 作为key
     * bitmap 作为value
     * @param url
     * @param bitmap
     */
    public static void setDiskCache(String url,Bitmap bitmap){
        try {
            //Each key must match the regex  [a-z0-9_-]{1,64}
            String cacheKey = getCacheKey(url);
            DiskLruCache.Editor editor = dlc.edit(cacheKey);
            if (editor != null) {
                OutputStream os = editor.newOutputStream(0);
                //将Bitmap对象压缩到输出流中，返回压缩结果
                boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                if (compress){
                    //压缩成功，提交数据，存入硬盘缓存
                    editor.commit();
                }else {
                    //压缩失败，回滚
                    editor.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取硬盘内存图片
     * @param url
     * @return
     */
    public static Bitmap getCacheBitmap(String url){
        try {
            //Each key must match the regex  [a-z0-9_-]{1,64}
            String cacheKey = getCacheKey(url);
            DiskLruCache.Snapshot snapshot = dlc.get(cacheKey);
            if (snapshot!=null){
                InputStream is = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  获取应用版本号
     * @param context
     * @return
     */
    private static int getVersonCode(Context context) {
        try {
            PackageInfo packageInfo
                    = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *  获取硬盘目录
     * @param context
     * @return
     */
    public static File getCacheDirectory(Context context) {
        //判断硬盘已经挂载且不能被移除
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
                &&  !Environment.isExternalStorageRemovable()){
            //外部缓存目录
            return context.getExternalCacheDir();
        }
        //内部缓存目录
        return context.getCacheDir();
    }

    /**
     * 更新日志文件：主要放到Activity的onDestory
     */
    public static void flush(){
        try {
            dlc.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭硬盘缓存
     */
    public static void close(){
        try {
            dlc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
