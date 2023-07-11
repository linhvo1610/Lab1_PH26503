package com.example.lab1_ph26503.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1_ph26503.R;

public class ToDoViewHolder extends RecyclerView.ViewHolder {
    public TextView txt_title_rcv;
    public TextView txt_content_rcv;
    public TextView txt_date_rcv;
    public TextView txt_type_rcv;
    public ImageView img_delete;
    public ImageView img_update;
    public CheckBox cb_status;

    public ToDoViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_title_rcv = itemView.findViewById(R.id.txt_title_rcv);
         txt_date_rcv= itemView.findViewById(R.id.txt_date_rcv);
         img_update = itemView.findViewById(R.id.img_update);
        img_delete = itemView.findViewById(R.id.img_delete);
        cb_status = itemView.findViewById(R.id.cb_confirm);
    }
}
