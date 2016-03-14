package com.administrator.youyaoqi.Activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.SousuoJieguoData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.listAdapter.SousuoJieguoAdapter;
import com.administrator.youyaoqi.utils.DownloadJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class SousuoMainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ImageButton ib_sousuo_item_back;
    private ImageButton ib_sousuo_item_do;
    private EditText et_sousuo_context;
    private ListView lv_sousuo_jieguo;
    private SousuoJieguoAdapter sja;
    private List<SousuoJieguoData.DataEntity.ReturnDataEntity.ComicListEntity> sousuoJieguoDatas;
    private String sousuoUrl;
    private LinearLayout ll_sousuo_no;
    private ImageView iv_sousuo_no;
    private TextView tv_sousuo_no;
    private ImageView iv_sousuomain_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo_main);
        //初始化
        iv_sousuomain_load = (ImageView)this.findViewById(R.id.iv_sousuomain_load);
        ib_sousuo_item_back = (ImageButton)this.findViewById(R.id.ib_sousuo_item_back);
        ib_sousuo_item_do = (ImageButton)this.findViewById(R.id.ib_sousuo_item_do);
        et_sousuo_context = (EditText)this.findViewById(R.id.et_sousuo_context);
        lv_sousuo_jieguo = (ListView)this.findViewById(R.id.lv_sousuo_jieguo);
        ll_sousuo_no = (LinearLayout)this.findViewById(R.id.ll_sousuo_no);
        iv_sousuo_no = (ImageView)this.findViewById(R.id.iv_sousuo_no);
        tv_sousuo_no = (TextView)this.findViewById(R.id.tv_sousuo_no);
        sousuoJieguoDatas = new ArrayList<>();
        sja = new SousuoJieguoAdapter(this,sousuoJieguoDatas);
        //设置适配器
        lv_sousuo_jieguo.setAdapter(sja);
        //设置点击监听
        lv_sousuo_jieguo.setOnItemClickListener(this);
        et_sousuo_context.setText(getIntent().getStringExtra("tag"));
        //默认搜索
        doSearch();

        //查找键
        ib_sousuo_item_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });

        //返回键
        ib_sousuo_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_sousuomain_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_sousuomain_load, "rotation", 0, 359);
                oa.setDuration(2000);
                oa.start();
                doSearch();
            }
        });

    }

    private void doSearch() {
        sousuoJieguoDatas.clear();
        String input0 = et_sousuo_context.getEditableText().toString();
        tv_sousuo_no.setText(input0);
        String input = null;
        try {
            input = URLEncoder.encode(input0, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sousuoUrl = "http://app.u17.com/v3/app/android/phone/search/rslist?q="
                + input
                + "&page="
                + 1
                + "&t="
                + new Date().getTime() / 1000
                + "&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";
        new GetSousuoDataTask().execute(sousuoUrl);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String comic_id = String.valueOf(sousuoJieguoDatas.get(position).getComic_id());
        String last_update_time = String.valueOf(sousuoJieguoDatas.get(position).getLast_update_time());
        String itemUrl = "http://app.u17.com/v3/app/android/phone/comic/detail_static?comicid="
                +comic_id
                +"&t="
                +last_update_time
                +"&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";

        Intent it = new Intent(this, GengxinItem.class);
        it.putExtra("itemUrl",itemUrl);
        startActivity(it);
    }

    class GetSousuoDataTask extends AsyncTask<String,Void,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_sousuomain_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return DownloadJson.httpGet(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s!=null){
                Gson gson = new Gson();
                SousuoJieguoData sjd = gson.fromJson(s,SousuoJieguoData.class);
                    if (sjd!=null){
                    sousuoJieguoDatas.addAll(sjd.getData().getReturnData().getComicList());
                        //当有数据时启动ListView,提醒适配器刷新
                        if (sousuoJieguoDatas.size()>1){
                            lv_sousuo_jieguo.setVisibility(View.VISIBLE);
                            ll_sousuo_no.setVisibility(View.GONE);
                            iv_sousuo_no.setVisibility(View.GONE);
                            sja.notifyDataSetChanged();
                            iv_sousuomain_load.setVisibility(View.GONE);
                        //当无数据，显示无数据
                        }else {
                            lv_sousuo_jieguo.setVisibility(View.GONE);
                            ll_sousuo_no.setVisibility(View.VISIBLE);
                            iv_sousuo_no.setVisibility(View.VISIBLE);
                            iv_sousuomain_load.setVisibility(View.GONE);
                        }
                }
            }
        }
    }
}
