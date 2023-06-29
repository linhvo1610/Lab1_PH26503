package com.example.lab1_ph26503.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab1_ph26503.R;
import com.example.lab1_ph26503.model.ToDoDTO;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    final ArrayList <ToDoDTO> toDoDTOArrayList;

    public TodoAdapter(ArrayList<ToDoDTO> toDoDTOArrayList) {
        this.toDoDTOArrayList = toDoDTOArrayList;
    }

    @Override
    public int getCount() {
        return toDoDTOArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return toDoDTOArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return toDoDTOArrayList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemview;
        if (view==null){
            itemview=View.inflate(viewGroup.getContext(), R.layout.layout_listview,null);

        }else itemview=view;
        ToDoDTO toDoDTO=(ToDoDTO) toDoDTOArrayList.get(i);
        TextView txt_title = itemview.findViewById(R.id.txt_title);
        TextView txt_content = itemview.findViewById(R.id.txt_content);
        TextView txt_date = itemview.findViewById(R.id.txt_date);
        TextView txt_type = itemview.findViewById(R.id.txt_type);
        txt_title.setText("Title: "+toDoDTO.getTitle());
        txt_content.setText("Content: "+toDoDTO.getContent());
        txt_date.setText("Date: "+toDoDTO.getDate());
        txt_type.setText("Type: "+toDoDTO.getType());
        return itemview;
    }
}
