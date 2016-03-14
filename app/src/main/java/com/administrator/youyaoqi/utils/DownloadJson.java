package com.administrator.youyaoqi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/8.
 */
public class DownloadJson {

    /**
     * HTTP  GET方法获取数据
     * @param param
     * @return
     */
    public static String httpGet(String param) {
        InputStream is = null;
        HttpURLConnection huc = null;

        try {
            URL url = new URL(param);
            huc = (HttpURLConnection) url.openConnection();
            huc.connect();

            if (huc.getResponseCode()==HttpURLConnection.HTTP_OK){
                is = huc.getInputStream();
                StringBuffer sb = new StringBuffer();
                int len = 0;
                byte[] buff = new byte[1024];
                while ((len = is.read(buff)) != -1){
                    sb.append(new String(buff,0,len));
                }
                return sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (huc != null){
                huc.disconnect();
            }
        }
        return null;
    }

}
