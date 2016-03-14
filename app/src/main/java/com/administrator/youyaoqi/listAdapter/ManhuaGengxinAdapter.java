package com.administrator.youyaoqi.listAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.ManhuaGengxinData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.utils.BitmapLoader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class ManhuaGengxinAdapter extends BaseAdapter{

    private List<ManhuaGengxinData.DataEntity.ReturnDataEntity> lists;
    private LayoutInflater lif;
    private Context context;

    public ManhuaGengxinAdapter(Context context,List<ManhuaGengxinData.DataEntity.ReturnDataEntity> lists) {
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
            convertView = lif.inflate(R.layout.lvitem_manhua_gengxin,null);
            vh.iv1 = (ImageView) convertView.findViewById(R.id.iv_manhua_gengxin_itempic);
            vh.tv_aut = (TextView) convertView.findViewById(R.id.tv_manhua_gengxin_author);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_manhua_gengxin_name);
            vh.tv_num = (TextView) convertView.findViewById(R.id.tv_manhua_gengxin_num);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_manhua_gengxin_time);
            vh.tv_type = (TextView) convertView.findViewById(R.id.tv_manhua_gengxin_type);
            vh.tv_now = (TextView) convertView.findViewById(R.id.tv_manhua_gengxin_now);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        //图片
        vh.iv1.setBackgroundResource(R.mipmap.bg_default_cover);
        vh.iv1.setTag(lists.get(position).getCover());
        BitmapLoader.loaderImage(context,lists.get(position).getCover(),vh.iv1);
        //漫画名
        vh.tv_name.setText(lists.get(position).getName());
        //作者
        vh.tv_aut.setText(lists.get(position).getNickname());
        //点击量
        vh.tv_num.setText(lists.get(position).getClick_total());
        //类型
        vh.tv_type.setText(lists.get(position).getTags().toString());
        //最近更新章节名
        vh.tv_now.setText(lists.get(position).getLast_update_chapter_name());
        //最近更新时间
        long update_time = lists.get(position).getLast_update_time();
        String now_day = new SimpleDateFormat("yyyyMMdd").format(new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(update_time*1000);
        String update_day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        long timeFlag = Long.parseLong(now_day) - Long.parseLong(update_day);
//        Log.i("ada",now_day+"   "+update_day+"  "+timeFlag+"    "+update_time+"    "+new Date().getTime());
        long time =  (new Date().getTime()/1000-update_time)/60;
        if(timeFlag>=1){
            vh.tv_time.setText("昨日更新");
        }  else {
            if (time>=60){
                vh.tv_time.setText(time/60+"小时前更新");
            }else if (time<60){
                vh.tv_time.setText(time+"分钟前更新");
            }
        }

        //背景
        if (position%2!=0){
            convertView.setBackgroundResource(R.drawable.bk);
        }else {
            convertView.setBackgroundResource(R.mipmap.bg_mine_head_portrait);
        }

        return convertView;
    }

    class ViewHolder{
        ImageView iv1;
        TextView tv_name;
        TextView tv_aut;
        TextView tv_num;
        TextView tv_type;
        TextView tv_time;
        TextView tv_now;
    }
}
