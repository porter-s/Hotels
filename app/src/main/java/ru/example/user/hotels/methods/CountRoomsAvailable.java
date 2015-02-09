package ru.example.user.hotels.methods;

/**
 * Created by user on 29.01.15.
 */
public class CountRoomsAvailable
{
    public static int countRoomsAvailable(String paramString)
    {
        int result=0;

        //Сразу считаем количество свободных номеров
        for (char element : paramString.toCharArray()){
            if (element == ':') result++;
        }

        if (result!=0) result++;
        if((result==0)&(paramString!=null)) result=1;

        return result;
    }
}