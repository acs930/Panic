package com.samples.alarmingsmock.panic;

import java.util.HashMap;

/**
 * Created by AlarmingSmock on 9/6/16.
 */
public class Constants {

    // Dictionary of sound names to their sound effect resource
    public static final HashMap<String, Integer> SoundDictionary;
    static
    {
        SoundDictionary = new HashMap<String, Integer>();
        SoundDictionary.put("Applause", R.raw.applause);
        SoundDictionary.put("BeHappy", R.raw.dont_worry_be_happy);
        SoundDictionary.put("FinalCountdown", R.raw.europe);
        SoundDictionary.put("Hakuna", R.raw.hakuna_matata);
        SoundDictionary.put("HallelujahS", R.raw.hallelujah_short);
        SoundDictionary.put("HallelujahL", R.raw.hallelujah_long);
        SoundDictionary.put("KeyboardCat", R.raw.keyboard_cat);
        SoundDictionary.put("OneUp", R.raw.mario_1up);
        SoundDictionary.put("Tada", R.raw.tada);
        SoundDictionary.put("FinalFantasyWin", R.raw.victory_ff);
        SoundDictionary.put("24", R.raw.twenty_four);
        SoundDictionary.put("BennyHill", R.raw.benny_hill);
        SoundDictionary.put("BarrelRoll", R.raw.do_a_barrel_roll);
        SoundDictionary.put("Drama", R.raw.drama);
        SoundDictionary.put("Dramatic", R.raw.dramatic);
        SoundDictionary.put("FlyFool", R.raw.fly_you_fools);
        SoundDictionary.put("Inception", R.raw.inception);
        SoundDictionary.put("Trap", R.raw.its_a_trap);
        SoundDictionary.put("KillBill", R.raw.kill_bill_fight);
        SoundDictionary.put("MetalGear", R.raw.metal_gear_alert);
        SoundDictionary.put("NukeAlarm", R.raw.nuclear_alarm);
        SoundDictionary.put("Trombone", R.raw.sad_trombone);
        SoundDictionary.put("Tuba", R.raw.sad_tuba);
        SoundDictionary.put("swNoooo", R.raw.star_wars_noooo);


    };


    // Dictionary of sound names to their sound effect resource
    public static final HashMap<String, Integer> VideoDictionary;
    static
    {
        VideoDictionary = new HashMap<String, Integer>();
        VideoDictionary.put("Reggie", R.raw.never_gonna_reggie);
    };

    // Dictionary of sound names to their sound effect resource
    public static final HashMap<String, Integer> PanicSoundBucket;
    static
    {
        PanicSoundBucket = new HashMap<String, Integer>();
        PanicSoundBucket.put("24", R.raw.twenty_four);
        PanicSoundBucket.put("BennyHill", R.raw.benny_hill);
        PanicSoundBucket.put("BarrelRoll", R.raw.do_a_barrel_roll);
        PanicSoundBucket.put("Drama", R.raw.drama);
        PanicSoundBucket.put("Dramatic", R.raw.dramatic);
        PanicSoundBucket.put("FlyFool", R.raw.fly_you_fools);
        PanicSoundBucket.put("Inception", R.raw.inception);
        PanicSoundBucket.put("Trap", R.raw.its_a_trap);
        PanicSoundBucket.put("KillBill", R.raw.kill_bill_fight);
        PanicSoundBucket.put("MetalGear", R.raw.metal_gear_alert);
        PanicSoundBucket.put("NukeAlarm", R.raw.nuclear_alarm);
        PanicSoundBucket.put("Trombone", R.raw.sad_trombone);
        PanicSoundBucket.put("Tuba", R.raw.sad_tuba);
        PanicSoundBucket.put("swNoooo", R.raw.star_wars_noooo);

    };

    // Dictionary of sound names to their sound effect resource
    public static final HashMap<String, Integer> NoPanicSoundBucket;
    static
    {
        NoPanicSoundBucket = new HashMap<String, Integer>();
        NoPanicSoundBucket.put("Applause", R.raw.applause);
        NoPanicSoundBucket.put("BeHappy", R.raw.dont_worry_be_happy);
        NoPanicSoundBucket.put("FinalCountdown", R.raw.europe);
        NoPanicSoundBucket.put("Hakuna", R.raw.hakuna_matata);
        NoPanicSoundBucket.put("HallelujahS", R.raw.hallelujah_short);
        NoPanicSoundBucket.put("HallelujahL", R.raw.hallelujah_long);
        NoPanicSoundBucket.put("KeyboardCat", R.raw.keyboard_cat);
        NoPanicSoundBucket.put("OneUp", R.raw.mario_1up);
        NoPanicSoundBucket.put("Tada", R.raw.tada);
        NoPanicSoundBucket.put("FinalFantasyWin", R.raw.victory_ff);
    };

}
