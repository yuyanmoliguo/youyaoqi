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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.LeixingItemData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.listAdapter.LeixingItemAdapter;
import com.administrator.youyaoqi.utils.DownloadJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class LeixingItem extends Activity implements AdapterView.OnItemClickListener{

    private LeixingItemAdapter lia;
    private TextView tv_leixing_top;
    private ListView lv_leixing;
    private ImageButton ib_leixing_item_back;
    private List<LeixingItemData.DataEntity.ReturnDataEntity> leixingItemDatas;
    private ImageView iv_fenlei_load;
    private String ItemUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_leixing_item);

        //接收
        Intent it = getIntent();
        String itemName = it.getStringExtra("ItemName");
        Log.i("js",itemName+"");
        ItemUrl = it.getStringExtra("ItemUrl");

        iv_fenlei_load = (ImageView)this.findViewById(R.id.iv_leixingitem_load);
        tv_leixing_top = (TextView)this.findViewById(R.id.tv_leixing_top);
        lv_leixing = (ListView)this.findViewById(R.id.lv_leixing);
        ib_leixing_item_back = (ImageButton)this.findViewById(R.id.ib_leixing_item_back);
        leixingItemDatas = new ArrayList<>();
        lia = new LeixingItemAdapter(this,leixingItemDatas);

        //设置标题
        tv_leixing_top.setText(itemName);

        //顶部返回
        ib_leixing_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //异步任务获取数据
        new GetLeixingItemTask().execute(ItemUrl);
        //关联适配器
        lv_leixing.setAdapter(lia);
        //设置列表点击监听
        lv_leixing.setOnItemClickListener(this);

        iv_fenlei_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_fenlei_load, "rotation", 0, 359);
                oa.setDuration(2000);
                oa.start();
                new GetLeixingItemTask().execute(ItemUrl);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String comic_id = String.valueOf(leixingItemDatas.get(position).getComic_id());
        String last_update_time = String.valueOf(leixingItemDatas.get(position).getLast_update_time());
        String itemUrl = "http://app.u17.com/v3/app/android/phone/comic/detail_static?comicid="
                +comic_id
                +"&t="
                +last_update_time
                +"&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";

        Intent it = new Intent(this, GengxinItem.class);
        it.putExtra("itemUrl",itemUrl);
        startActivity(it);
    }

    class GetLeixingItemTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_fenlei_load.setVisibility(View.VISIBLE);
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
                LeixingItemData pid = gson.fromJson(s,LeixingItemData .class);
                if (pid!=null){
                    leixingItemDatas.addAll(pid.getData().getReturnData());
                    lia.notifyDataSetChanged();
                    iv_fenlei_load.setVisibility(View.GONE);
                }
            }
        }
    }

}
