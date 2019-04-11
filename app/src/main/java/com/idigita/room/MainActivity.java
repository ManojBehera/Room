package com.idigita.room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.idigita.room.adapter.TodoListAdapter;
import com.idigita.room.db.ToDo;
import com.idigita.room.model.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT = 1;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private List<ToDo> toDoList = null;
    private TodoListAdapter todoListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        initializeList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivityForResult(intent,RESULT);
            }
        });
    }

    private void initializeList() {
        toDoList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new DBOperation().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT){
            String message = data.getStringExtra("message");
            if(message.equalsIgnoreCase("success")){
                new DBOperation().execute();
            }else{
                Toast.makeText(MainActivity.this,"data not saved!",Toast.LENGTH_LONG).show();
            }
        }else{

        }
    }

    public class DBOperation extends AsyncTask<Void,Void,List<ToDo>>{

        @Override
        protected List<ToDo> doInBackground(Void... voids) {
            toDoList = Model.getInstance(MainActivity.this).getMyDatabase().getDao().getLists();
            return toDoList;
        }

        @Override
        protected void onPostExecute(List<ToDo> toDoList) {
            super.onPostExecute(toDoList);
            //new TodoListAdapter(toDoList).notifyDataSetChanged();
            todoListAdapter = new TodoListAdapter(toDoList);
            recyclerView.setAdapter(todoListAdapter);
            todoListAdapter.notifyDataSetChanged();
        }
    }
}
