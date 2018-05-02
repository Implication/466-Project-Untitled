package com.example.dusk.loginpage;

/**
 * Created by Jared Fipps on 2/25/2018.
 */

public class CardsJava {
    private String mText1;
    private String mHour;
    private String mMinute;
    private String mColon;

    public CardsJava(String Text1, String hour, String minute, String colon)
    {
        mText1 = Text1;
        mHour = hour;
        mMinute = minute;
        mColon = colon;
    }

    public String getTitleText () {
        return mText1;
    }
    public String getMHour () {
        return mHour;
    }
    public String getMMinute () {
        return mMinute;
    }
    public String getMColon () { return mColon; }

}
