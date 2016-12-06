package com.samples.alarmingsmock.panic.Helpers;

import android.content.Context;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.samples.alarmingsmock.panic.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by AlarmingSmock on 9/6/16.
 */

// Singleton controller for the Media Player, might need a better name
public class RemoteControl implements MediaPlayer.OnPreparedListener{

    public static final String TAG = RemoteControl.class.getName();

    private RemoteControl()
    {
        // Any initialization here
    }

    private static class Holster
    {
        private static final RemoteControl instance = new RemoteControl();
    }

    public static RemoteControl getInstance()
    {
        return Holster.instance;
    }

    MediaPlayer leMediaPlayer;
    private boolean playerStatus = false;

    private static MediaPlayer oldMediaPlayer;

    //Because it is difficult loading/preparing a media player manually use this for immediate results
    public void quickPlaySound(Context currentContext, String fileName, Map fileStore){

        if(oldMediaPlayer != null){
            oldMediaPlayer.release();
        }

        int resourceId = (int) fileStore.get(fileName);
        MediaPlayer mp = MediaPlayer.create(currentContext, resourceId);

        mp.start();
        playerStatus = true;

        oldMediaPlayer = mp;
        oldMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playerStatus = false;
            }
        });
    }

    private void initializeMediaPlayer()
    {
        if(leMediaPlayer == null)
        {
            leMediaPlayer = new MediaPlayer();
        } else
        {
            leMediaPlayer.reset();
        }

        playerStatus = !leMediaPlayer.equals(null);
    }

    // release and nullify the media player instance
    // then create a brand new media player
    //TODO look up if setting things to null has any effect
    private void refreshMediaPlayer()
    {
        leMediaPlayer.release();
        leMediaPlayer = null;

        leMediaPlayer = new MediaPlayer();
        playerStatus = !leMediaPlayer.equals(null);
    }

    private void disposeMediaPlayer()
    {
        leMediaPlayer.stop();
        leMediaPlayer.release();
        leMediaPlayer = null;
        playerStatus = false;
    }


    public void loadAndPlaySound(Context currentContext, String fileName, Map fileStore)
    {
        if(!playerStatus){
            this.initializeMediaPlayer();
        }

        try {
            int resourceId = (int) fileStore.get(fileName);
            String resourcePath = buildResourcePath(currentContext, "test.mp3");

            leMediaPlayer.setDataSource(resourcePath);
            leMediaPlayer.prepare();
            leMediaPlayer.start();
            //prepareListener();
            //TODO maybe set a display surface here, also find out what a display surface is


        } catch(FileNotFoundException e)
        {

        } catch (IOException e){

        }
    }

    public void loadAndPlaySound(Context currentContext, Uri fileUri)
    {
        if(!playerStatus){
            this.initializeMediaPlayer();
        }
        try {
            leMediaPlayer.setDataSource(currentContext, fileUri);
            prepareListener();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void stopPlaying(){
        if(playerStatus)
        {
            if(leMediaPlayer != null) {
                leMediaPlayer.stop();
            }
            if(oldMediaPlayer != null) {
                oldMediaPlayer.stop();
            }
        } else {
            Log.d(TAG, "Player is already stopped");
        }
    }

    public void loadAndPlaySound(int rawId)
    {

    }

    // This does not work as the path to the actual file doesn't have the resource id
    /*private String buildResourcePath(Context currentContext, int resourceId)
    {
        return "android.resource://"+currentContext.getPackageName()+"/raw/"+resourceId;
    }*/

    private String buildResourcePath(Context currentContext, String fileName)
    {
        return "android.resource://"+currentContext.getPackageName()+"/raw/"+fileName;
    }

    // The media player ahs to be prepared before it can play a sound
    // This will always be run before attempting to play a sound and the listener will be called when it is ready
    private void prepareListener()
    {
        leMediaPlayer.setOnPreparedListener(this);
        leMediaPlayer.prepareAsync();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        leMediaPlayer.start();
    }
}
