package com.administrator.youyaoqi.pagerAdapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.utils.BitmapLoader;
import com.administrator.youyaoqi.utils.DownloadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class ReadMainPagerAdapter extends PagerAdapter {

        private List<View> viewList;
        private String readUrl;
//        private ImageView iv_read_main;
        private Context context;


    public ReadMainPagerAdapter(Context context,String readUrl,List<View> viewList) {
            this.viewList = viewList;
            this.readUrl = readUrl;
            this.context = context;
        }

        @Override
        public int getCount() {
            return viewList==null?0:viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View)object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            if (viewList!=null){
                view = viewList.get(position);
                new ReadDownload(view,position).execute(readUrl);
                container.addView(view);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (viewList!=null){
                container.removeView(viewList.get(position));
            }
        }


    class ReadDownload extends AsyncTask<String,Void,String> {

        private View view;
        private int num;

        public ReadDownload(View view,int num) {
            this.view = view;
            this.num = num;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return DownloadJson.httpGet(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s!=null){
                try {
                    JSONObject obj = new JSONObject(s);
                    JSONObject data = obj.getJSONObject("data");
                    JSONArray returnData = data.getJSONArray("returnData");
                    String pic;
                    JSONObject zhangjie = returnData.getJSONObject(num);
                    if (zhangjie.has("location")){
                        pic = zhangjie.getString("location");
                    }else {
                        pic = " ";
                    }
                    ImageView iv = (ImageView)view.findViewById(R.id.iv_read_main);
                    iv.setTag(pic);
                    Log.i("urlll",pic);
                    BitmapLoader.loaderImage(context, pic, iv);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
