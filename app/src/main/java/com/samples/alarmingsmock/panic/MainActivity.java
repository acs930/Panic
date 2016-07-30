package com.samples.alarmingsmock.panic;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    //So the idea here is that you pick the mode (PANIC, notPANIC)
    //Then press the button, randomly select a sound clip and play it (you can pile on sounds)

    //But how about every once in a while (either state? just panic?)  you get reggie rolled/rick rolled
    //Could also add a secret code to force override the next button press

    //video would be loaded from webview or something... (404 not found)

    public static Context mContext;
    public ViewHolder viewHolder;

    private boolean PANICMODE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart(){
        super.onStart();
        mContext = null;
        mContext = getApplicationContext();
        _initView();
    }

    public void _initView(){
        viewHolder = new ViewHolder();

        viewHolder.panicButton = (Button)findViewById(R.id.panic_button);
        viewHolder.panicSwitch = (Switch)findViewById(R.id.panic_switch);

        viewHolder.panicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateState(isChecked);
            }
        });

        viewHolder.panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //panic or not here
            }
        });

    }

    private void updateState(boolean state){


        if(state){
            PANICMODE = true;
            viewHolder.panicButton.setBackground(mContext.getResources().getDrawable(R.drawable.panic_button_on_selector));
        } else {
            PANICMODE = false;
            viewHolder.panicButton.setBackground(mContext.getResources().getDrawable(R.drawable.panic_button_off_selector));
        }
    }

    private class ViewHolder
    {
        Button panicButton;
        Switch panicSwitch;

    }
}
