package com.popland.pop.myvideonews_youtubeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hai on 26/07/2016.
 */
public class CustomListAdapter extends ArrayAdapter<VideoFeed> {
    LayoutInflater inflater = null;
    public CustomListAdapter(Context context, int resource, List<VideoFeed> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(getContext());
    }
class ViewHolder{
    ImageView imageView;
    TextView TVname;
}
    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.custom_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            viewHolder.TVname = (TextView)convertView.findViewById(R.id.TVname);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        VideoFeed p = getItem(position);
        if(p!=null){
            viewHolder.imageView.setImageResource(p.logo);
            viewHolder.TVname.setText(p.name);
        }
        return convertView;
    }
}
