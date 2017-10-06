package com.popland.pop.myvideonews_youtubeapi;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hai on 26/07/2016.
 */
public class FragmentVideoNews extends ListFragment {
String[] mangVideoId;
    String[]   mangName = {"NASA","GOOGLE","APPLE","CNET"};
    int[] mangImage = {R.drawable.nasa,R.drawable.android,R.drawable.apple,R.drawable.cnet};
    ArrayList<VideoFeed> arrl;
    Communicator com;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com = (Communicator) getActivity();
        mangVideoId = getResources().getStringArray(R.array.VideolistID);
        arrl = new ArrayList<VideoFeed>();
        for(int i=0;i<mangName.length;i++){
            arrl.add(new VideoFeed(mangImage[i],mangName[i],mangVideoId[i]));
        }
        CustomListAdapter adapter = new CustomListAdapter(getActivity(),R.layout.custom_listview,arrl);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
       com.NhanDuLieu(arrl.get(position).videoList);
       // Toast.makeText(getActivity(),arrl.get(position).videoList,Toast.LENGTH_LONG).show();
    }
}
