package com.administrator.youyaoqi.listAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.ManhuaFeileiData;
import com.administrator.youyaoqi.Bean.ManhuaSousuoDATA;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.utils.BitmapLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class ManhuaSousuoAdapter extends BaseAdapter{

    private List<ManhuaSousuoDATA.DataEntity.ReturnDataEntity> lists;
    private LayoutInflater lif;
    private Context context;

    public ManhuaSousuoAdapter(Context context,List<ManhuaSousuoDATA.DataEntity.ReturnDataEntity> lists) {
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
            convertView = lif.inflate(R.layout.gvitem_manhua_sousuo,null);
            vh.tv = (TextView) convertView.findViewById(R.id.tv_manhua_sousuo_item);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(lists.get(position).getTag());
        int bgColor = Color.parseColor(lists.get(position).getBgColor());
        convertView.setBackgroundColor(bgColor);
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
