package com.MyApp_3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSettings;
    Button buttonRed,buttonYellow,buttonGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = findViewById(R.id.RelativeLayout);;
        mSettings = getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        if (mSettings.contains("my_background_color")) {
            int color = mSettings.getInt("my_background_color", 0);
            relativeLayout.setBackgroundColor(color);
        }

        buttonRed = findViewById(R.id.buttonRed);
        buttonYellow = findViewById(R.id.buttonYellow);
        buttonGreen = findViewById(R.id.buttonGreen);

        buttonRed.setOnClickListener(this);
        buttonYellow.setOnClickListener(this);
        buttonGreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRed:{
                RedColor();
                break;
            }
            case R.id.buttonYellow:{
                YellowColor();
                break;
            }
            case R.id.buttonGreen:{
                GreenColor();
                break;
            }
        }
    }

    public void  RedColor(){
        RelativeLayout relativeLayout = findViewById(R.id.RelativeLayout);;
        relativeLayout.setBackgroundColor(Color.RED);
    }

    public void  YellowColor(){
        RelativeLayout relativeLayout = findViewById(R.id.RelativeLayout);;
        relativeLayout.setBackgroundColor(Color.YELLOW);
    }

    public void  GreenColor(){
        RelativeLayout relativeLayout = findViewById(R.id.RelativeLayout);;
        relativeLayout.setBackgroundColor(Color.GREEN);
    }

    public void  onStop() {
        super.onStop();
        RelativeLayout relativeLayout = findViewById(R.id.RelativeLayout);;
        SharedPreferences.Editor editor = mSettings.edit();
        Drawable background = relativeLayout.getBackground();
        if (background instanceof ColorDrawable)
            editor.putInt("my_background_color", ((ColorDrawable) background).getColor());
        editor.apply();
    }
}