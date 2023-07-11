package com.example.lab1_ph26503;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1_ph26503.Adapter.TodoAdapter;
import com.example.lab1_ph26503.DAO.ToDoDAO;
import com.example.lab1_ph26503.model.ToDoDTO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText input_title,input_content,input_date,input_type;
    ToDoDAO toDoDAO;
    TodoAdapter todoAdapter;
    ListView lv_todo;
    RecyclerView todo_rcv;
    ArrayList<ToDoDTO> arrayList;
    ToDoDTO currentObj = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_title = findViewById(R.id.input_title);
        input_content = findViewById(R.id.input_content);
        input_date = findViewById(R.id.input_date);
        input_type = findViewById(R.id.input_type);

        toDoDAO = new ToDoDAO(this);
        toDoDAO.open();

        arrayList = toDoDAO.GetAll();
        todoAdapter = new TodoAdapter(arrayList,toDoDAO);
//        lv_todo = findViewById(R.id.lv_todo);
        todo_rcv = findViewById(R.id.rcl_todo);
        todo_rcv.setAdapter(todoAdapter);
//        lv_todo.setAdapter(todoAdapter);
//        lv_todo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                currentObj = arrayList.get(i);
//                input_title.setText(currentObj.getTitle());
//                input_content.setText(currentObj.getContent());
//                input_date.setText(currentObj.getDate());
//                input_type.setText(currentObj.getType());
//                return false;
//            }
//        });
    }

    public void AddTodo(View view){
        ToDoDTO toDoDTO= new ToDoDTO();
        toDoDTO.setTitle(input_title.getText().toString());
        toDoDTO.setContent(input_content.getText().toString());
        toDoDTO.setDate(input_date.getText().toString());
        toDoDTO.setType(input_type.getText().toString());
        long ketqua = toDoDAO.Addnew(toDoDTO);
        if(ketqua>0){
            arrayList.clear();
            arrayList.addAll(toDoDAO.GetAll());
            todoAdapter.notifyDataSetChanged();
            input_title.setText("");
            input_content.setText("");
            input_date.setText("");
            input_type.setText("");
            Toast.makeText(this,"Them moi thanh cong"+ketqua,Toast.LENGTH_LONG).show();
        }else Toast.makeText(this,"Them moi that bai"+ketqua,Toast.LENGTH_LONG).show();

    }
    public void UpdateTodo(View view){
        String new_input_title = input_title.getText().toString();
        String new_input_content = input_content.getText().toString();
        String new_input_date = input_date.getText().toString();
        String new_input_type = input_type.getText().toString();
        if(currentObj!=null&&(!currentObj.getTitle().equalsIgnoreCase(new_input_title)||!currentObj.getContent().equalsIgnoreCase(new_input_content)
                ||!currentObj.getDate().equalsIgnoreCase(new_input_date)||!currentObj.getType().equalsIgnoreCase(new_input_type))){
            currentObj.setTitle(new_input_title);
            currentObj.setContent(new_input_content);
            currentObj.setDate(new_input_date);
            currentObj.setType(new_input_type);
            int res = toDoDAO.Update(currentObj);
            if(res>0){
                input_title.setText("");
                input_content.setText("");
                input_date.setText("");
                input_type.setText("");
                arrayList.clear();
                arrayList.addAll(toDoDAO.GetAll());
                todoAdapter.notifyDataSetChanged();
                Toast.makeText(this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
                currentObj = null;
            }else{
                Toast.makeText(this,"Lỗi",Toast.LENGTH_LONG).show();
            }
        }else Toast.makeText(this,"Không có gì để cập nhật  ",Toast.LENGTH_LONG).show();
    }
    public void DeleteTodo(View view){
        if(currentObj!=null){
            int res = toDoDAO.Delete(currentObj);
            if(res>0){
                arrayList.clear();
                arrayList.addAll(toDoDAO.GetAll());
                todoAdapter.notifyDataSetChanged();
                input_title.setText("");
                input_content.setText("");
                input_date.setText("");
                input_type.setText("");
                Toast.makeText(this,"Xóa thành công",Toast.LENGTH_LONG).show();
                currentObj = null;
            }else {
                Toast.makeText(this, "Lỗi", Toast.LENGTH_LONG).show();
            }
        }else Toast.makeText(this,"Bấm và giữ 1 bản khi nào đó trước khi xóa  ",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toDoDAO.close();
    }
}