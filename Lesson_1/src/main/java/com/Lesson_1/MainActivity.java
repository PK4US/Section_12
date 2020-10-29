package com.Lesson_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonLoad = findViewById(R.id.buttonLoad);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        buttonSave.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSave: {
                save();
                break;
            }
            case R.id.buttonLoad: {
                load();
                break;
            }
            case R.id.buttonDelete: {
                delete();
                break;
            }
        }
    }

    private void save(){
        String text = ((EditText)findViewById(R.id.et_text)).getText().toString();
        FileOutputStream out = null;
        try {
            out = openFileOutput(FILE_NAME,MODE_APPEND);
            out.write(text.getBytes());
            out.close();
            showToast("В файл добавленны данные");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Ошибка при записи файла");
        }
    }

    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void load(){

    }

    private void delete(){

    }
}