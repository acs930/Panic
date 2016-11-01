package com.samples.alarmingsmock.panic.Helpers;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.animation.RotateAnimation;

import java.util.Date;

/**
 * Created by AlarmingSmock on 10/18/16.
 */
public class ScreenControl {

    public static final String TAG = RemoteControl.class.getName();

    private ScreenControl()
    {
        // Any initialization here
    }

    private static class Holster
    {
        private static final ScreenControl instance = new ScreenControl();
    }

    public static ScreenControl getInstance()
    {
        return Holster.instance;
    }

    View currentView;

    boolean initFlag = false;
    boolean onFlag = false;


    public void SetCurrentView(View inputView)
    {
        this.currentView = inputView;

    }

    public void FlipCurrentView()
    {
        if(this.currentView != null)
        {
            float currentRot = this.currentView.getRotation();
            float halfRotation = (currentRot +180) % 360;

            RotateAnimation rotAnimation = new RotateAnimation(currentRot, halfRotation);

            this.currentView.startAnimation(rotAnimation);
        }
    }

    public void FlipCurrentViewDuration(int seconds)
    {
        long start = System.currentTimeMillis();
        long waitMilli = seconds * 1000;
        long end = start + waitMilli;

        while(System.currentTimeMillis() < end)
        {
            this.FlipCurrentView();
        }
    }

    public void FlipCurrentViewLoop()
    {
        onFlag = true;
        while (onFlag)
        {
            this.FlipCurrentView();
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //This assumes that the newView has been inflated
    public void InjectView(View newView)
    {
        int left = this.currentView.getLeft();
        int right = this.currentView.getRight();
        int top = this.currentView.getTop();
        int bottom = this.currentView.getBottom();

        this.currentView.layout(0,0,0,0);
        newView.layout(left, top, right, bottom);

        newView.invalidate();
    }



}
