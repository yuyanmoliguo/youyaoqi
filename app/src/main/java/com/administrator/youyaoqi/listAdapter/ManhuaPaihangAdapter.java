package com.administrator.youyaoqi.listAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.ManhuaPaihangData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.utils.BitmapLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class ManhuaPaihangAdapter extends BaseAdapter {

//    private ManhuaPaihangData.DataEntity.ReturnDataEntity lists;
    private List<ManhuaPaihangData.DataEntity.ReturnDataEntity.RankinglistEntity> lists;
    private LayoutInflater lif;
    private Context context;

    public ManhuaPaihangAdapter(Context context,List<ManhuaPaihangData.DataEntity.ReturnDataEntity.RankinglistEntity>  lists) {
        this.lists = lists;
        this.context = context;
        lif = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = lif.inflate(R.layout.lvitem_manhua_paihang,null);
            vh.pic = (ImageView) convertView.findViewById(R.id.iv_manhua_paihang_itempic);
            vh.tv1 = (TextView) convertView.findViewById(R.id.tv_manhua_paihang_1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.tv_manhua_paihang_2);
            vh.tv3 = (TextView) convertView.findViewById(R.id.tv_manhua_paihang_3);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }


        //图片
        vh.pic.setBackgroundResource(R.mipmap.bg_default_cover);
        vh.pic.setTag(lists.get(position).getCover());
        BitmapLoader.loaderImage(context, lists.get(position).getCover(), vh.pic);
        //文字信息
        vh.tv1.setText(lists.get(position).getRankingName());
        vh.tv2.setText(lists.get(position).getRankingDescription1());
        vh.tv3.setText(lists.get(position).getRankingDescription2());
        //背景
        if (position%2!=0){
            convertView.setBackgroundResource(R.drawable.bk);
        }else {
            convertView.setBackgroundResource(R.mipmap.bg_mine_head_portrait);
        }

        return convertView;
    }

    class ViewHolder{
        ImageView pic;
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }
}
