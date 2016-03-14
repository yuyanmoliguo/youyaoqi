package com.administrator.youyaoqi.Activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.PaihangItemData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.listAdapter.PaihangItemAdapter;
import com.administrator.youyaoqi.utils.DownloadJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class PanghangItem extends Activity implements AdapterView.OnItemClickListener{

    private ListView lv_paihang;
    private List<PaihangItemData.DataEntity.ReturnDataEntity> paihangItemDatas;
    private PaihangItemAdapter pia;
    private TextView tv_paihang_top;
    private ImageButton ib_paihang_item_back;
    private ImageView iv_paihangitem_load;
    private String paiItemUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paihang_item);
        //接收
        Intent it = getIntent();
        String itemName = it.getStringExtra("itemName");
        paiItemUrl = it.getStringExtra("ItemUrl");
        //初始化
        tv_paihang_top = (TextView)this.findViewById(R.id.tv_paihang_top);
        iv_paihangitem_load = (ImageView)this.findViewById(R.id.iv_paihangitem_load);
        lv_paihang = (ListView)this.findViewById(R.id.lv_paihang);
        ib_paihang_item_back = (ImageButton)this.findViewById(R.id.ib_paihang_item_back);
        paihangItemDatas = new ArrayList<>();
        pia = new PaihangItemAdapter(this,paihangItemDatas);

        //设置标题
        tv_paihang_top.setText(itemName);

        //顶部返回
        ib_paihang_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //异步任务获取数据
        new GetPaihangItemTask().execute(paiItemUrl);
        //关联适配器
        lv_paihang.setAdapter(pia);
        //设置列表点击监听
        lv_paihang.setOnItemClickListener(this);

        iv_paihangitem_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_paihangitem_load, "rotation", 0, 359);
                oa.setDuration(2000);
                oa.start();
                new GetPaihangItemTask().execute(paiItemUrl);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String comic_id = String.valueOf(paihangItemDatas.get(position).getComic_id());
        String last_update_time = String.valueOf(paihangItemDatas.get(position).getLast_update_time());
        String itemUrl = "http://app.u17.com/v3/app/android/phone/comic/detail_static?comicid="
                +comic_id
                +"&t="
                +last_update_time
                +"&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";

        Intent it = new Intent(this, GengxinItem.class);
        it.putExtra("itemUrl",itemUrl);
        startActivity(it);
    }

    class GetPaihangItemTask extends AsyncTask<String,Void,String> {
        @Override
         protected void onPreExecute() {
            super.onPreExecute();
            iv_paihangitem_load.setVisibility(View.VISIBLE);
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
                PaihangItemData pid = gson.fromJson(s,PaihangItemData.class);
                if (pid!=null){
                    paihangItemDatas.addAll(pid.getData().getReturnData());
                    pia.notifyDataSetChanged();
                    iv_paihangitem_load.setVisibility(View.GONE);
                }
            }
        }
    }
}
