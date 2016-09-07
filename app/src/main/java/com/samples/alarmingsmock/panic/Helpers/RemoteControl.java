package com.samples.alarmingsmock.panic.Helpers;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Dictionary;

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

    private void intializeMediaPlayer()
    {
        if(leMediaPlayer != null)
        {
            leMediaPlayer = new MediaPlayer();
        } else
        {
            leMediaPlayer.reset();
        }
    }

    // release and nullify the media player instance
    // then create a brand new media player
    //TODO look up if setting things to null has any effect
    private void refreshMediaPlayer()
    {
        leMediaPlayer.release();
        leMediaPlayer = null;

        leMediaPlayer = new MediaPlayer();
    }

    private void disposeMediaPlayer()
    {

    }

    public void loadAndPlaySound(Context currentContext, String fileName, Dictionary fileStore)
    {
        
    }

    public void loadAndPlaySound(int rawId)
    {

    }
}
