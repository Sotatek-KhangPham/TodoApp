package com.example.chien.todoapp;

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
    private List<Todo> list;
    private static Handler handler;

    public void setHandler(Handler handler)
    {
        this.handler=handler;
    }


    public ToDoAdapter(){

     }

     public void setList(List<Todo> listTodo)
     {
         this.list = listTodo;
     }

    public void updateRecyclerView(List<Todo> list1)
    {
        this.list.clear();

        this.list.addAll(list1);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);

        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
         Todo todo = list.get(position);
         holder.setData(todo);
    }

    @Override
    public int getItemCount() {
        return list.size();
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
                handler.onItemClick(list.get(getAdapterPosition()));
            }

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            handler.onCheckChange(isChecked);
        }
    }
}
