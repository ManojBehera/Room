package com.idigita.room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.idigita.room.db.ToDo;
import com.idigita.room.model.Model;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText jobTitle;
    private EditText jobDetail;
    private Button btnChange;
    private TextView mHeader;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);
        jobTitle = (EditText) findViewById(R.id.job_title);
        jobDetail = (EditText)findViewById(R.id.job_details);
        mHeader = (TextView) findViewById(R.id.header);
        btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnChange:
                boolean fieldEmpty = validateFields();
                if(!fieldEmpty){
                    new DBOperation().execute();
                }else{
                    Toast.makeText(this,"Enter Valid Data!",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void updateData() {
        ToDo toDo = new ToDo();
        toDo.setJobTitle(jobTitle.getText().toString());
        toDo.setJobDetail(jobDetail.getText().toString());
        Model.getInstance(TaskActivity.this).getMyDatabase().getDao().insertData(toDo);

    }

    private boolean validateFields() {
        if(TextUtils.isEmpty(jobTitle.getText().toString())){
            return true;
        }else if(TextUtils.isEmpty(jobDetail.getText().toString())){
            return true;
        }else{
            return false;
        }
    }

    public class DBOperation extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            updateData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent();
            intent.putExtra("message","success");
            setResult(1,intent);
            finish();
        }
    }
}
