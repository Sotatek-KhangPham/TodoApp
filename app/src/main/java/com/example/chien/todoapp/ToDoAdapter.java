package com.example.chien.todoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chien.todoapp.DBLocal.Models.Todo;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.TodoViewHolder> {
    private List<Todo> listTodo;
    private static Handler handler;
    private final LayoutInflater mInflater;
    public void setHandler(Handler handler)
    {
        this.handler=handler;
    }


    public ToDoAdapter(Context context){
        mInflater = LayoutInflater.from(context);
     }

     public void setList(List<Todo> listTodo)
     {
         this.listTodo = listTodo;
         notifyDataSetChanged();
     }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater
                .inflate(R.layout.todo_item, parent, false);

        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        if(listTodo != null)
        {
            Todo todo = listTodo.get(position);
            holder.setData(todo);
        }
        else
        {
            holder.txtTitle.setText("Không có dữ liệu.");
        }

    }

    @Override
    public int getItemCount() {
        if (listTodo != null)
            return listTodo.size();
        return 0;
    }



    public class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener
    {
        TextView txtTitle;
        TextView txtCreatedDate;
        CheckBox checkBox;

        public TodoViewHolder(View itemView) {
            super(itemView);
            txtTitle= (TextView)itemView.findViewById(R.id.txtTitle);
            txtCreatedDate = (TextView) itemView.findViewById(R.id.txtDate);
            checkBox = (CheckBox) itemView.findViewById(R.id.ckb_complete);

            checkBox.setOnCheckedChangeListener(this);
            itemView.setOnClickListener(this);
        }

        public void setData(Todo todo)
        {
            txtTitle.setText(todo.getTitle());
            txtCreatedDate.setText(todo.getCreatedDate().toString());
            checkBox.setChecked(todo.getCompleted());
        }

        @Override
        public void onClick(View v) {
            //go to detail
            if(handler != null)
            {
                handler.onItemClick(listTodo.get(getAdapterPosition()));
            }

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            handler.onCheckChange(isChecked);
        }
    }
}
