package ru.example.user.hotels.objects;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ru.example.user.hotels.abstracts.ObjectData;
import ru.example.user.hotels.methods.JsonParser;
import ru.example.user.hotels.methods.RequestData;
import ru.example.user.hotels.types.ListHotelsType;

/**
 * Created by user on 29.01.15.
 */
public class ObjectList extends ObjectData
{
    public static ArrayList<ListHotelsType> listHotels;

    public Object getData()
    {
        return listHotels;
    }

    public String getInfo()
    {
        return null;
    }

    public boolean loadData(Context paramContext, String url)
    {
        try
        {
            new RequestData();
            if (RequestData.requestData(url) != null)
            {
                JsonParser localJsonParser = new JsonParser();
                new RequestData();
                JSONArray localJSONArray = new JSONArray(RequestData.requestData(url));
                listHotels = localJsonParser.jsonParserList(paramContext, localJSONArray);
            }
            else
            {
                listHotels = null;
            }
        }
        catch (JSONException localJSONException)
        {
            localJSONException.printStackTrace();
            Log.e("ObjectList", "Error loadData");
            listHotels = null;
        }
        return true;
    }
}