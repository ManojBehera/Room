package com.idigita.room.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.idigita.room.R;
import com.idigita.room.db.ToDo;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder> {
    private List<ToDo> toDoList;

    public TodoListAdapter(List<ToDo> toDoList){
        this.toDoList = toDoList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todo_row,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        ToDo toDo = toDoList.get(position);
        myViewHolder.mTitle.setText(toDo.getJobTitle());
        myViewHolder.mDetail.setText(toDo.getJobDetail());
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle,mDetail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.title);
            mDetail = (TextView)itemView.findViewById(R.id.detail);
        }
    }
}
