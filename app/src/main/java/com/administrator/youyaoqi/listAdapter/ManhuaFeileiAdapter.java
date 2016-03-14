package com.administrator.youyaoqi.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.ManhuaFeileiData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.utils.BitmapLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class ManhuaFeileiAdapter extends BaseAdapter {

    private List<ManhuaFeileiData.DataEntity.ReturnDataEntity.RankinglistEntity> lists;
    private LayoutInflater lif;
    private Context context;

    public ManhuaFeileiAdapter(Context context,List<ManhuaFeileiData.DataEntity.ReturnDataEntity.RankinglistEntity> lists) {
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
            convertView = lif.inflate(R.layout.gvitem_manhua_feilei,null);
            vh.iv = (ImageView) convertView.findViewById(R.id.iv_manhua_feilei_item);
            vh.tv = (TextView) convertView.findViewById(R.id.tv_manhua_feilei_item);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        //图片
        vh.iv.setBackgroundResource(R.mipmap.bg_default_cover);
        vh.iv.setTag(lists.get(position).getCover());
        BitmapLoader.loaderImage(context, lists.get(position).getCover(), vh.iv);
        //文字信息
        vh.tv.setText(lists.get(position).getSortName());
        return convertView;
    }

    class ViewHolder{
        TextView tv;
        ImageView iv;
    }
}
