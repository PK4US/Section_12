package com.MyApp_2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSave = findViewById(R.id.buttonRandNumber);
        buttonSave.setOnClickListener(this);
    }

    public void onClick(View view) {
        TextView tvRandNumber = findViewById(R.id.tvRandNumber);
        Random random = new Random();
        tvRandNumber.setText(Integer.toString(random.nextInt(1000) + 1));

        String text = tvRandNumber.getText().toString();
        try {
            FileOutputStream out = openFileOutput(FILE_NAME,MODE_APPEND);
            out.write(text.getBytes());
            out.close();
            showToast("В файл добавленны данные");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Ошибка при записи файла");
        }
    }

    public void onClickLoadNum(View view) {
        try {
            FileInputStream in = openFileInput(FILE_NAME);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            String s = new String(buffer);
            ((TextView)findViewById(R.id.tvReadNumberFromFile)).setText(s);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Ошибка при чтении файла!");
        }
    }

    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}