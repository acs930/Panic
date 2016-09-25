package com.samples.alarmingsmock.panic;

import android.media.SoundPool;
import android.net.Uri;

import java.io.File;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlarmingSmock on 9/6/16.
 */
public class Constants {

    // Dictionary of sound names to their sound effect resource
    public static final HashMap<String, Integer> SoundDictionary;
    static
    {
        SoundDictionary = new HashMap<String, Integer>();
        SoundDictionary.put("Reggie", R.raw.never_gonna_reggie);
    };

}
