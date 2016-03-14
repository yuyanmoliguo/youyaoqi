package com.administrator.youyaoqi.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.GengxinItemData;
import com.administrator.youyaoqi.R;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class GengxinMuluAdapter extends BaseAdapter{

    private List<GengxinItemData> lists;
    private LayoutInflater lif;

    public GengxinMuluAdapter(Context context,List<GengxinItemData> lists) {
        this.lists = lists;
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
            convertView = lif.inflate(R.layout.gengxin_mulu,null);
            vh.tv1 = (TextView) convertView.findViewById(R.id.tv_mulu_name);
            vh.tv2 = (TextView) convertView.findViewById(R.id.tv_mulu_num);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        //章节名
        vh.tv1.setText(lists.get(position).getName());
        //章节篇幅
        vh.tv2.setText("("+lists.get(position).getNum()+"p)");

        //背景
        if (position%2!=0){
            convertView.setBackgroundResource(R.drawable.bk);
        }else {
            convertView.setBackgroundResource(R.mipmap.bg_mine_head_portrait);
        }

        return convertView;
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
    }
}
