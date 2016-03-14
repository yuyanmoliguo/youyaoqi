package com.administrator.youyaoqi.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.youyaoqi.Activity.ReadActivity;
import com.administrator.youyaoqi.Bean.GengxinItemData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.constant.UrlConstants;
import com.administrator.youyaoqi.listAdapter.GengxinMuluAdapter;
import com.administrator.youyaoqi.myapplication.MyApplication;
import com.administrator.youyaoqi.utils.BitmapLoader;
import com.administrator.youyaoqi.utils.DownloadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class GengxinItemFragment extends Fragment implements AdapterView.OnItemClickListener{

    private String itemUrl;
    private RelativeLayout rl_gengxin_item;
    private int numFlag;
    private TextView tv_gengxin_item_context_no;
    private ImageView iv_gengxin_item_context_no;
    private TextView fl0;
    private TextView lx0;
    private TextView zyp0;
    private TextView ztc0;
    private TextView zdj0;
    private TextView zpl0;
    private TextView ztp0;
    private TextView byyp0;
    private TextView tv_gengxin_item_context_yes;
    private ImageView iv_gengxin_item_context_yes;
    private TextView tv_gengxin_item_contextfenlei;
    private TextView tv_gengxin_item_contextleixing;
    private TextView tv_gengxin_item_contextzongyuepiao;
    private TextView tv_gengxin_item_contextzongtucao;
    private TextView tv_gengxin_item_contextzongdianji;
    private TextView tv_gengxin_item_contextzongpinglun;
    private TextView tv_gengxin_item_contextzongtupian;
    private TextView tv_gengxin_item_contextbenyueyuepiao;
    private ImageView gengxin_item_pic;
    private TextView gengxin_item_name;
    private TextView gengxin_item_time;
    private TextView gengxin_item_aut;
    private TextView gengxin_item_num;
    private List<GengxinItemData> muluList;
    private List<GengxinItemData> muluListT;
    private List<GengxinItemData> muluListF;
    private GengxinMuluAdapter gma;
    private ListView lv_gengxin_item_mulu;
    private ImageButton ib_gengxin_item_back;
    private ImageButton ib_gengxin_item_sx;
    int tfFlag=0;
    private ImageView iv_gengxinitem_load;
    private String name;

    public GengxinItemFragment(String url){
        itemUrl = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fl_gengxin_item,null);

        View view1 = inflater.inflate(R.layout.gengxin_item_context,null);
        //倒序
        ib_gengxin_item_sx = (ImageButton)view1.findViewById(R.id.ib_gengxin_item_sx);
        ib_gengxin_item_sx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muluList.clear();
                if (tfFlag%2==0){
                    muluList.addAll(muluListF);
                }else {
                    muluList.addAll(muluListT);
                }
                gma.notifyDataSetChanged();
                tfFlag++;
            }
        });

        iv_gengxinitem_load = (ImageView)view.findViewById(R.id.iv_gengxinitem_load);

        //点击查看隐藏内容效果
        initContext(view1);
        //设置数据
        setData(view,view1);
        //返回按钮监听
        ib_gengxin_item_back = (ImageButton)view.findViewById(R.id.ib_gengxin_item_back);
        ib_gengxin_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

//        iv_gengxinitem_load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_gengxinitem_load, "rotation", 0, 359);
//                oa.setDuration(2000);
//                oa.start();
//                new GetGengxinItemDataTask().execute(itemUrl);
//            }
//        });

        return view;
    }


    /**
     * 设置数据
     * @param view
     */
    private void setData(View view,View view1) {
        //初始化
        lv_gengxin_item_mulu = (ListView)view.findViewById(R.id.lv_gengxin_item_mulu);

        muluList = new ArrayList<>();
        muluListT = new ArrayList<>();
        muluListF = new ArrayList<>();
        gma = new GengxinMuluAdapter(getContext(),muluList);
        lv_gengxin_item_mulu.setAdapter(gma);

        if (muluListT!=null&&muluListF!=null){
            lv_gengxin_item_mulu.addHeaderView(view1);
        }

        //启动异步任务下载Json数据
        new GetGengxinItemDataTask().execute(itemUrl);
        //监听Item点击
        lv_gengxin_item_mulu.setOnItemClickListener(GengxinItemFragment.this);
    }

    /**
     * 点击跳转阅读界面
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String readUrl = "http://app.u17.com/v3/app/android/phone/comic/chapter?chapter_id="
//                            +muluList.get(position-1).getId()
//                            +"&t="
//                            +new Date().getTime()/1000
//                            +"&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";
//        Log.i("nummm", position + "");

        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        myApplication.setMuluListT(muluListT);

        Intent it = new Intent(getActivity(),ReadActivity.class);
        it.putExtra("pagerNow", muluList.get(position-1).getId());
        it.putExtra("picNum", muluList.get(position -1).getPicNum());
        it.putExtra("id",position-1+"");
        it.putExtra("name",name);
        it.putExtra("modeFlag","1");
        startActivity(it);
    }

    /**
     * 异步任务下载Json数据，并更新视图
     */
    class GetGengxinItemDataTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_gengxinitem_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return DownloadJson.httpGet(params[0]);
        }

        /**
         * 解析Json 更新视图
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String urlPic = null;
            String time = null;
            String num = null;
            String aut = null;
            String cont = null;
            String fenlei = null;
            String leixing = null;
            String zongyuepiao = null;
            String zongtucao = null;
            String zongpinglun = null;
            String zongtupian = null;
            String benyueyuepiao = null;


//            Log.i("lll", s);
            if (s!=null){
                try {
                    JSONObject obj = new JSONObject(s);
                    JSONObject data = obj.getJSONObject("data");
                    JSONObject returnData = data.getJSONObject("returnData");
                    JSONObject comic = returnData.getJSONObject("comic");

                    if (comic.has("cover")){
                        urlPic = comic.getString("cover");
                    }else {
                        urlPic = " ";
                    }
                    if (comic.has("name")){
                        name = comic.getString("name");
                    }else {
                        name = " ";
                    }
                    if (comic.has("last_update_time")){
                        time = comic.getString("last_update_time");
                    }else {
                        time = " ";
                    }
                    if (comic.has("click_total")){
                        num = comic.getString("click_total");
                    }else {
                        num = " ";
                    }
                    if (comic.has("description")){
                        cont = comic.getString("description");
                    }else {
                        cont = " ";
                    }
                    if (comic.has("theme_ids")){
                        fenlei = comic.getString("theme_ids");
                    }else {
                        fenlei = " ";
                    }
                    if (comic.has("cate_id")){
                        leixing = comic.getString("cate_id");
                    }else {
                        leixing = " ";
                    }
                    if (comic.has("total_ticket")){
                        zongyuepiao = comic.getString("total_ticket");
                    }else {
                        zongyuepiao = " ";
                    }
                    if (comic.has("total_tucao")){
                        zongtucao = comic.getString("total_tucao");
                    }else {
                        zongtucao = " ";
                    }
                    if (comic.has("comment_total")){
                        zongpinglun = comic.getString("comment_total");
                    }else {
                        zongpinglun = " ";
                    }
                    if (comic.has("image_all")){
                        zongtupian = comic.getString("image_all");
                    }else {
                        zongtupian = " ";
                    }
                    if (comic.has("month_ticket")){
                        benyueyuepiao = comic.getString("month_ticket");
                    }else {
                        benyueyuepiao = " ";
                    }

//                    Log.i("gx",benyueyuepiao);

                    JSONObject authorJson = comic.getJSONObject("author");
                    if (authorJson.has("name")){
                        aut = authorJson.getString("name");
                    }else {
                        aut = " ";
                    }


                    JSONArray chapter_list = returnData.getJSONArray("chapter_list");
                    for (int i=0;i<chapter_list.length();i++){
                        JSONObject mulu = chapter_list.getJSONObject(i);
                        String zhangjieName = null;
                        String zhangjieNum = null;
                        String zhangjieid = null;
                        String zhangjiepic = null;
                        if (mulu.has("name")){
                            zhangjieName = mulu.getString("name");
                        }else {
                            zhangjieName = " ";
                        }
                        if (mulu.has("image_total")){
                            zhangjieNum = mulu.getString("image_total");
                        }else {
                            zhangjieNum = " ";
                        }
                        if (mulu.has("chapter_id")){
                            zhangjieid = mulu.getString("chapter_id");
                        }else {
                            zhangjieid = " ";
                        }

                        if (mulu.has("image_total")){
                            zhangjiepic = mulu.getString("image_total");
                        }else {
                            zhangjiepic = " ";
                        }
//                        Log.i("sssss",zhangjieName+zhangjieNum);

                        GengxinItemData gid = new GengxinItemData();
                        gid.setName(zhangjieName);
                        gid.setNum(zhangjieNum);
                        gid.setId(zhangjieid);
                        gid.setPicNum(zhangjiepic);
                        muluListT.add(gid);
                        gma.notifyDataSetChanged();
                    }
                    muluList.addAll(muluListT);
                    for (int i=muluList.size()-1;i>0;i--){
                        muluListF.add(muluListT.get(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    iv_gengxinitem_load.setVisibility(View.GONE);
                    //图片
                    gengxin_item_pic.setTag(urlPic);
                    BitmapLoader.loaderImage(getContext()
                            , urlPic
                            , gengxin_item_pic);
                    //名称
                    gengxin_item_name.setText(name);
                    //更新时间
                    long time1 = Long.parseLong(time);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(time1 * 1000);
                    String time2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                    gengxin_item_time.setText(time2);
                    //总点击
                    gengxin_item_num.setText(num);
                    tv_gengxin_item_contextzongdianji.setText(num);
                    //作者
                    gengxin_item_aut.setText(aut);
                    //简介
                    tv_gengxin_item_context_no.setText(cont);
                    tv_gengxin_item_context_yes.setText(cont);
                    //分类
                    tv_gengxin_item_contextfenlei.setText(fenlei);
                    //类型
                    tv_gengxin_item_contextleixing.setText(leixing);
                    //总月票
                    tv_gengxin_item_contextzongyuepiao.setText(zongyuepiao);
                    //总吐槽
                    tv_gengxin_item_contextzongtucao.setText(zongtucao);
                    //总评论
                    tv_gengxin_item_contextzongpinglun.setText(zongpinglun);
                    //总图片
                    tv_gengxin_item_contextzongtupian.setText(zongtupian);
                    //本月月票
                    tv_gengxin_item_contextbenyueyuepiao.setText(benyueyuepiao);
                }
            }
        }
    }


    /**
     * 点击查看隐藏内容效果
     */
    private void initContext(View view) {
        //初始化
        rl_gengxin_item = (RelativeLayout)view.findViewById(R.id.rl_gengxin_item);
        tv_gengxin_item_context_no = (TextView)view.findViewById(R.id.tv_gengxin_item_context_no);
        iv_gengxin_item_context_no = (ImageView)view.findViewById(R.id.iv_gengxin_item_context_no);
        tv_gengxin_item_context_yes = (TextView)view.findViewById(R.id.tv_gengxin_item_context_yes);
        iv_gengxin_item_context_yes = (ImageView)view.findViewById(R.id.iv_gengxin_item_context_yes);
        fl0 = (TextView)view.findViewById(R.id.fl0);
        lx0 = (TextView)view.findViewById(R.id.lx0);
        zyp0 = (TextView)view.findViewById(R.id.zyp0);
        ztc0 = (TextView)view.findViewById(R.id.ztc0);
        zdj0 = (TextView)view.findViewById(R.id.zdj0);
        zpl0 = (TextView)view.findViewById(R.id.zpl0);
        ztp0 = (TextView)view.findViewById(R.id.ztp0);
        byyp0 = (TextView)view.findViewById(R.id.byyp0);
        tv_gengxin_item_contextfenlei = (TextView)view.findViewById(R.id.tv_gengxin_item_contextfenlei);
        tv_gengxin_item_contextleixing = (TextView)view.findViewById(R.id.tv_gengxin_item_contextleixing);
        tv_gengxin_item_contextzongyuepiao = (TextView)view.findViewById(R.id.tv_gengxin_item_contextzongyuepiao);
        tv_gengxin_item_contextzongtucao = (TextView)view.findViewById(R.id.tv_gengxin_item_contextzongtucao);
        tv_gengxin_item_contextzongdianji = (TextView)view.findViewById(R.id.tv_gengxin_item_contextzongdianji);
        tv_gengxin_item_contextzongpinglun = (TextView)view.findViewById(R.id.tv_gengxin_item_contextzongpinglun);
        tv_gengxin_item_contextzongtupian = (TextView)view.findViewById(R.id.tv_gengxin_item_contextzongtupian);
        tv_gengxin_item_contextbenyueyuepiao = (TextView)view.findViewById(R.id.tv_gengxin_item_contextbenyueyuepiao);
        gengxin_item_pic = (ImageView)view.findViewById(R.id.gengxin_item_pic);
        gengxin_item_name = (TextView)view.findViewById(R.id.gengxin_item_name);
        gengxin_item_time = (TextView)view.findViewById(R.id.gengxin_item_time);
        gengxin_item_aut = (TextView)view.findViewById(R.id.gengxin_item_aut);
        gengxin_item_num = (TextView)view.findViewById(R.id.gengxin_item_num);
        numFlag = 0;
        //监听点击效果
        rl_gengxin_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numFlag%2!=0){
                    tv_gengxin_item_context_no.setVisibility(View.VISIBLE);
                    iv_gengxin_item_context_no.setVisibility(View.VISIBLE);
                    tv_gengxin_item_context_yes.setVisibility(View.GONE);
                    iv_gengxin_item_context_yes.setVisibility(View.GONE);
                    fl0.setVisibility(View.GONE);
                    lx0.setVisibility(View.GONE);
                    zyp0.setVisibility(View.GONE);
                    ztc0.setVisibility(View.GONE);
                    zdj0.setVisibility(View.GONE);
                    zpl0.setVisibility(View.GONE);
                    ztp0.setVisibility(View.GONE);
                    byyp0.setVisibility(View.GONE);
                    tv_gengxin_item_contextfenlei.setVisibility(View.GONE);
                    tv_gengxin_item_contextleixing.setVisibility(View.GONE);
                    tv_gengxin_item_contextzongyuepiao.setVisibility(View.GONE);
                    tv_gengxin_item_contextzongtucao.setVisibility(View.GONE);
                    tv_gengxin_item_contextzongdianji.setVisibility(View.GONE);
                    tv_gengxin_item_contextzongpinglun.setVisibility(View.GONE);
                    tv_gengxin_item_contextzongtupian.setVisibility(View.GONE);
                    tv_gengxin_item_contextbenyueyuepiao.setVisibility(View.GONE);
                }else {
                    tv_gengxin_item_context_no.setVisibility(View.GONE);
                    iv_gengxin_item_context_no.setVisibility(View.GONE);
                    tv_gengxin_item_context_yes.setVisibility(View.VISIBLE);
                    iv_gengxin_item_context_yes.setVisibility(View.VISIBLE);
                    fl0.setVisibility(View.VISIBLE);
                    lx0.setVisibility(View.VISIBLE);
                    zyp0.setVisibility(View.VISIBLE);
                    ztc0.setVisibility(View.VISIBLE);
                    zdj0.setVisibility(View.VISIBLE);
                    zpl0.setVisibility(View.VISIBLE);
                    ztp0.setVisibility(View.VISIBLE);
                    byyp0.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextfenlei.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextleixing.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextzongyuepiao.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextzongtucao.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextzongdianji.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextzongpinglun.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextzongtupian.setVisibility(View.VISIBLE);
                    tv_gengxin_item_contextbenyueyuepiao.setVisibility(View.VISIBLE);
                }
                numFlag++;
            }
        });
    }
}
