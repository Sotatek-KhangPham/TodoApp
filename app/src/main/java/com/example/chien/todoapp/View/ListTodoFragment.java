package com.example.chien.todoapp.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.Handler;
import com.example.chien.todoapp.R;
import com.example.chien.todoapp.Repositorys.TodoRepository;
import com.example.chien.todoapp.ToDoAdapter;
import com.example.chien.todoapp.ViewData.TodoViewModel;

import java.util.List;


public class ListTodoFragment extends Fragment implements Handler {

    private Button btnAddTodo;
    private Button btnDelete;
    private RecyclerView recyclerView;
    private TodoRepository todoRepository;
    private ToDoAdapter adapter;
    List<Todo> listTodo;
    TodoViewModel viewModel;

    private OnFragmentInteractionListener mListener;

    public ListTodoFragment() {

    }
    public void setTodoRepository(TodoRepository todoRepository)
    {
        this.todoRepository = todoRepository;
    }
    public static ListTodoFragment newInstance() {
        ListTodoFragment fragment = new ListTodoFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_todo, container, false);
        recyclerView =(RecyclerView)v.findViewById(R.id.recyclerView);
        btnAddTodo =(Button)v.findViewById(R.id.addTodo);
        btnDelete =(Button)v.findViewById(R.id.btnDelete);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ToDoAdapter(listTodo);
        recyclerView.setAdapter(adapter);
        adapter.setHandler(this);
        viewModel.setTodoRepositor(todoRepository);
        viewModel.initData();
        viewModel.getData().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable List<Todo> list) {
                listTodo = list;
                adapter.updateRecyclerView(list);
            }
        });

        btnAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonAddClick();

            }
        });
        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        viewModel = ViewModelProviders.of(ListTodoFragment.this).get(TodoViewModel.class);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(Todo todo) {

    }

    @Override
    public void onCheckChange(Boolean status) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onButtonAddClick();
    }
}
