package ru.example.user.hotels.abstracts;

import android.content.Context;

/**
 * Created by user on 29.01.15.
 */

public abstract class ObjectData
{
    private Object Object;

    public abstract Object getData();

    public abstract String getInfo();

    public abstract boolean loadData(Context paramContext, String paramString);
}

