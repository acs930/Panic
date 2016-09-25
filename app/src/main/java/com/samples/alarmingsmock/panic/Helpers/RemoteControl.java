package com.samples.alarmingsmock.panic.Helpers;

import android.content.Context;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by AlarmingSmock on 9/6/16.
 */

// Singleton controller for the Media Player, might need a better name
public class RemoteControl {

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

    private void initializeMediaPlayer()
    {
        if(leMediaPlayer != null)
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

    public void loadAndPlaySound(Context currentContext, String fileName, Dictionary fileStore)
    {
        if(!playerStatus){
            this.initializeMediaPlayer();
        }

        try {
            int resourceId = (int) fileStore.get(fileName);
            String resourcePath = buildResourcePath(currentContext, resourceId);

            leMediaPlayer.setDataSource(resourcePath);
            //TODO maybe set a display surface here, also find out what a display surface is
            leMediaPlayer.start();

        } catch(FileNotFoundException e)
        {

        } catch (IOException e){

        }

        /*try{

        }catch (IOException e){
            e.printStackTrace();
        }*/
    }

    public void loadAndPlaySound(Context currentContext, Uri fileUri)
    {
        if(!playerStatus){
            this.initializeMediaPlayer();
        }
        try {
            leMediaPlayer.setDataSource(currentContext, fileUri);
            leMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAndPlaySound(int rawId)
    {

    }

    private String buildResourcePath(Context currentContext, int resourceId)
    {
        return "android.resource://"+currentContext.getPackageName()+"raw/"+resourceId;
    }
}
