package com.sparta.waj;

import java.text.DecimalFormat;

public class Performance
{
    private long startTime;
    private long lastStampTime = 0;
    private DecimalFormat formatter = new DecimalFormat("###,###");

    private static Performance performance;

    private Performance() {}


    public static Performance getInstance()
    {
        if (performance == null)
        {
            performance = new Performance();
        }
        return performance;
    }


    public void startTimer()
    {
        startTime = System.nanoTime();
    }

    public String getElapsedTime()
    {
        long timeTaken = System.nanoTime() - startTime;
        String printTime = formatter.format(timeTaken);
        timeStamp();
        return printTime;
    }

    public void timeStamp()
    {
        lastStampTime = System.nanoTime();
    }

    public String getTimeSinceLastStamp()
    {
        if (lastStampTime == 0)
        {
            lastStampTime = System.nanoTime();
            return getElapsedTime();
        }

        long timeTaken = System.nanoTime() - lastStampTime;
        String printTime = formatter.format(timeTaken);
        lastStampTime = System.nanoTime();
        return printTime;
    }

}
