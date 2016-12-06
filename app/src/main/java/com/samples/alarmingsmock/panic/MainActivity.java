package com.samples.alarmingsmock.panic;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.VideoView;

import com.samples.alarmingsmock.panic.Helpers.RemoteControl;
import com.samples.alarmingsmock.panic.Helpers.ScreenControl;

import java.io.File;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //So the idea here is that you pick the mode (PANIC, notPANIC)
    //Then press the button, randomly select a sound clip and play it (you can pile on sounds)

    //But how about every once in a while (either state? just panic?)  you get reggie rolled/rick rolled
    //Could also add a secret code to force override the next button press

    //video would be loaded from webview or something... (404 not found)

    //TODO so now i can add API calls to news outlets to determine news and when you open the app it has a status plugged in
    //TODO Still manually overridable.  But now i think a chatbot can be made and for the mattermost server
    //TODO the bot will then email my(whoevers) work email which will have a low power panic app service monitoring email
    //TODO when the service is triggered it will alert me(whoever) with a notification to open the app.
    //TODO the app should open up to panic mode and will be pressed thouroughly before the switch will unlock
    //TODO then they can click on the Don't Panic button to be taken to the email...


    public static Context mContext;
    public ViewHolder viewHolder;

    private boolean PANICMODE = false;
    private static final String TAG = MainActivity.class.getSimpleName();

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
        viewHolder.stopButton = (Button)findViewById(R.id.stop_button);
        viewHolder.invisibleButton = (Button)findViewById(R.id.invisible_button);
        viewHolder.panicSwitch = (Switch)findViewById(R.id.panic_switch);
        viewHolder.animationWindow = (VideoView)findViewById(R.id.animation_window);

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
                playSound();
            }
        });
        viewHolder.invisibleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        viewHolder.stopButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoteControl.getInstance().stopPlaying();
                    }
                }

        );

    }

    //Ping the Panic server to get the current panic state
    private void initPanic(){

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
        Button invisibleButton;
        Button stopButton;
        Switch panicSwitch;
        VideoView animationWindow;

    }


    //#region Sound Stuff
    public void playSound()
    {

        Random rand = new Random(System.currentTimeMillis());
        int rNum;

        RemoteControl.getInstance().stopPlaying();
        if(PANICMODE){
            Object[] panicArray = Constants.PanicSoundBucket.keySet().toArray();
            rNum = rand.nextInt(panicArray.length);
            RemoteControl.getInstance().quickPlaySound(mContext, (String) panicArray[rNum], Constants.PanicSoundBucket);

            //RemoteControl.getInstance().loadAndPlaySound(mContext, (String)panicArray[rNum], Constants.PanicSoundBucket);
        } else {
            Object[] dontPanicArray = Constants.NoPanicSoundBucket.keySet().toArray();
            rNum = rand.nextInt(dontPanicArray.length);

            RemoteControl.getInstance().quickPlaySound(mContext, (String)dontPanicArray[rNum], Constants.NoPanicSoundBucket);

            //RemoteControl.getInstance().loadAndPlaySound(mContext, (String)dontPanicArray[rNum], Constants.NoPanicSoundBucket);
        }

    }

    public void playSoundSpecific(String filename)
    {

    }


    public void playVideo()
    {

        Log.d(TAG, "Video is starting");

        String localThree = "android.resource://" + getPackageName() + File.separator + R.raw.never_gonna_reggie;

        Uri video = Uri.parse(localThree);

        viewHolder.animationWindow.bringToFront();
        viewHolder.animationWindow.setVisibility(View.VISIBLE);

        viewHolder.animationWindow.setVideoPath(localThree);

        viewHolder.animationWindow.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //mp.setLooping(true);
                viewHolder.animationWindow.start();
            }
        });

        //viewHolder.animationWindow.getSettings().setPluginsEnabled(true);
        //viewHolder.animationWindow.getSettings().setPluginState(WebSettings.PluginState.ON);//setPluginsEnabled(true);

        //viewHolder.animationWindow.loadUrl(localOne);
    }

    //TODO I want to be able to inject a view into a view without needing to have it specified in the xml.
    //TODO It would involve grabbing the xml of the layout and adding the new view that I've created into it.
    //TODO I know it would be easier to grab just make a basylayout that has this and do things with it

    private boolean lightFlag = PANICMODE;
    public void ActivateLights(boolean state)
    {

    }

    public void fuDraw(Canvas screen)
    {
        int midW = screen.getWidth();
        Paint p = new Paint();
        p.setColor(Color.DKGRAY);
        screen.drawLine(midW/2, 0, midW/2, screen.getHeight(), p);

    }

    public void AlignLights(){
        lightFlag = PANICMODE;
    }

    public void TestScreen0()
    {
        viewHolder.animationWindow.setVisibility(View.VISIBLE);


        ScreenControl.getInstance().SetCurrentView(viewHolder.animationWindow);
        ScreenControl.getInstance().FlipCurrentView();
    }


    public void TestScreen1()
    {
        viewHolder.animationWindow.setVisibility(View.VISIBLE);
        if(!PANICMODE) {
            viewHolder.animationWindow.setBackgroundColor(Color.LTGRAY);
        } else {
            viewHolder.animationWindow.setBackgroundColor(Color.YELLOW);
        }

        ScreenControl.getInstance().SetCurrentView(viewHolder.animationWindow);
        ScreenControl.getInstance().FlipCurrentView();
    }

    public void TestScreen2()
    {
        viewHolder.animationWindow.setVisibility(View.VISIBLE);
        if(!PANICMODE) {
            viewHolder.animationWindow.setBackgroundColor(Color.LTGRAY);
        } else {
            viewHolder.animationWindow.setBackgroundColor(Color.YELLOW);
        }

        ScreenControl.getInstance().SetCurrentView(viewHolder.animationWindow);
        ScreenControl.getInstance().FlipCurrentViewDuration(10);
    }

}
