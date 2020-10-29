package com.Lesson_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileInputStream;
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
        try {
            FileOutputStream out = openFileOutput(FILE_NAME,MODE_APPEND);
            out.write(text.getBytes());
            out.close();
            showToast("В файл добавленны данные!");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Ошибка при записи файла!");
        }
    }

    private void load(){
        try {
            FileInputStream in = openFileInput(FILE_NAME);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            String s = new String(buffer);
            ((TextView)findViewById(R.id.tv)).setText(s);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Ошибка при чтении файла!");
        }
    }

    private void delete(){
       if (deleteFile(FILE_NAME)) showToast("Файл успешно удалён!");
       else showToast("Ошибка при удалении файла!");
    }

    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}