package com.example.lab1_ph26503.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1_ph26503.DAO.ToDoDAO;
import com.example.lab1_ph26503.R;
import com.example.lab1_ph26503.model.ToDoDTO;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {
    Context context;
    ArrayList<ToDoDTO> listTodo;
    ToDoDAO toDoDAO;


    public TodoAdapter(ArrayList<ToDoDTO> list, ToDoDAO toDoDAO) {
        this.listTodo = list;
        this.toDoDAO = toDoDAO;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view_of_item= inflater.inflate(R.layout.layout_recycleview,parent,false);
        ToDoViewHolder toDoViewHolder=new ToDoViewHolder(view_of_item);

        return toDoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        final int index = position;
            ToDoDTO list = listTodo.get(index);
        holder.txt_title_rcv.setText(list.getTitle());
        holder.txt_date_rcv.setText(list.getDate());
        if(listTodo.get(position).getStatus()==1){
            holder.cb_status.setChecked(true);
            holder.txt_title_rcv.setPaintFlags(holder.txt_title_rcv.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

        }else{
            holder.cb_status.setChecked(false);
            holder.txt_title_rcv.setPaintFlags(holder.txt_title_rcv.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialogDelete(list,index);
            }
        });
        holder.cb_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int id = listTodo.get(holder.getAdapterPosition()).getId();
                boolean check_result = toDoDAO.updateStatusToDO(id,holder.cb_status.isChecked());

                if(check_result){
                    Toast.makeText(context,"Đã update Status thành công",Toast.LENGTH_SHORT).show();
                    listTodo.clear();
                    listTodo= toDoDAO.GetAll();


                }else{
                    Toast.makeText(context,"Update Status không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void dialogDelete(ToDoDTO toDoDTO, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa " + toDoDTO.getTitle());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = toDoDAO.Delete(toDoDTO);
                if (res > 0) {
                    listTodo.remove(index);
                    notifyItemRemoved(index);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return listTodo.size();
    }
//    final ArrayList <ToDoDTO> toDoDTOArrayList;
//
//    public TodoAdapter(ArrayList<ToDoDTO> toDoDTOArrayList) {
//        this.toDoDTOArrayList = toDoDTOArrayList;
//    }
//
//    @Override
//    public int getCount() {
//        return toDoDTOArrayList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return toDoDTOArrayList.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return toDoDTOArrayList.get(i).getId();
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//
//        View itemview;
//        if (view==null){
//            itemview=View.inflate(viewGroup.getContext(), R.layout.layout_listview,null);
//
//        }else itemview=view;
//        ToDoDTO toDoDTO=(ToDoDTO) toDoDTOArrayList.get(i);
//        TextView txt_title = itemview.findViewById(R.id.txt_title);
//        TextView txt_content = itemview.findViewById(R.id.txt_content);
//        TextView txt_date = itemview.findViewById(R.id.txt_date);
//        TextView txt_type = itemview.findViewById(R.id.txt_type);
//        txt_title.setText("Title: "+toDoDTO.getTitle());
//        txt_content.setText("Content: "+toDoDTO.getContent());
//        txt_date.setText("Date: "+toDoDTO.getDate());
//        txt_type.setText("Type: "+toDoDTO.getType());
//        return itemview;
//    } listview

}
