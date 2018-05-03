package com.example.dusk.loginpage;

/**
 * Created by Jared Fipps on 2/25/2018.
 */

public class CardsJava {
    private String mText1;
    private String mHour;
    private String mMinute;
    private String mColon;
    private String mMonth;
    private String mDay;
    private String mSlash;

    public CardsJava(String Text1, String hour, String minute, String colon, String month, String day, String slash)
    {
        mText1 = Text1;
        mHour = hour;
        mMinute = minute;
        mColon = colon;
        mMonth = month;
        mDay = day;
        mSlash = slash;
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
    public String getMMonth () { return mMonth; }
    public String getMDay () { return mDay; }
    public String getMSlash () { return mSlash; }

}
