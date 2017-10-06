package com.popland.pop.myvideonews_youtubeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hai on 26/07/2016.
 */
public class CustomBaseAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Video> myItems;
    LayoutInflater inflater =null;
    public CustomBaseAdapter(Context context,int layout,List<Video> items){
        myContext = context;
        myLayout = layout;
        myItems = items;
        inflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
class ViewHolder{
    ImageView thumbnail;
    TextView TVtitle, TVpublishedAt;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView ==null){
            convertView = inflater.inflate(myLayout,null);
            viewHolder = new ViewHolder();
            viewHolder.thumbnail = (ImageView)convertView.findViewById(R.id.thumbnail);
            viewHolder.TVtitle = (TextView)convertView.findViewById(R.id.TVtitle);
            viewHolder.TVpublishedAt = (TextView)convertView.findViewById(R.id.TVpublishedAt);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.TVtitle.setText(myItems.get(position).title);
        viewHolder.TVpublishedAt.setText(myItems.get(position).publishedAt);
        Picasso.with(myContext).load(myItems.get(position).url).into(viewHolder.thumbnail);
        return convertView;
    }
}
