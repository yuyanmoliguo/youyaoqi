package com.administrator.youyaoqi.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.SousuoJieguoData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.utils.BitmapLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class SousuoJieguoAdapter extends BaseAdapter{

    private List<SousuoJieguoData.DataEntity.ReturnDataEntity.ComicListEntity> lists;
    private LayoutInflater lif;
    private Context context;

    public SousuoJieguoAdapter(Context context,List<SousuoJieguoData.DataEntity.ReturnDataEntity.ComicListEntity> lists) {
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
            convertView = lif.inflate(R.layout.lv_sousuojieguo_item,null);
            vh.iv_pic = (ImageView) convertView.findViewById(R.id.iv_sousuo_item_pic);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_sousuo_item_name);
            vh.tv_aut = (TextView) convertView.findViewById(R.id.sousuo_item_aut);
            vh.tv_num = (TextView) convertView.findViewById(R.id.sousuo_item_num);
            vh.tv_type = (TextView) convertView.findViewById(R.id.sousuo_item_type);
            vh.tv_value = (TextView) convertView.findViewById(R.id.sousuo_item_value);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        //图片
        vh.iv_pic.setBackgroundResource(R.mipmap.bg_default_cover);
        vh.iv_pic.setTag(lists.get(position).getCover());
        BitmapLoader.loaderImage(context, lists.get(position).getCover(), vh.iv_pic);
        //名称
        vh.tv_name.setText(lists.get(position).getName());
        //作者
        vh.tv_aut.setText(lists.get(position).getNickname());
        //点击量
        vh.tv_num.setText(lists.get(position).getClick_total());
        //类型
        vh.tv_type.setText(lists.get(position).getTags().toString());
        //简介
        vh.tv_value.setText(lists.get(position).getDescription());

        //背景
        if (position%2!=0){
            convertView.setBackgroundResource(R.drawable.bk);
        }else {
            convertView.setBackgroundResource(R.mipmap.bg_mine_head_portrait);
        }

        return convertView;
    }

    class ViewHolder{
        TextView tv_name;
        TextView tv_aut;
        TextView tv_num;
        TextView tv_type;
        TextView tv_value;
        ImageView iv_pic;
    }

}
