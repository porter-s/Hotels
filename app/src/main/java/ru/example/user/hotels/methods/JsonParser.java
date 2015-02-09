package ru.example.user.hotels.methods;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.example.user.hotels.R;
import ru.example.user.hotels.types.InfoHotelType;
import ru.example.user.hotels.types.ListHotelsType;

/**
 * Created by user on 29.01.15.
 */
public class JsonParser {

    public InfoHotelType jsonParserInfo(Context paramContext, JSONObject paramJSONObject) {
        InfoHotelType localInfoHotelType = new InfoHotelType();
        if (paramJSONObject != null) ;
        {
            try {
                localInfoHotelType.id = paramJSONObject.getInt("id");
                localInfoHotelType.name = paramJSONObject.getString("name");
                localInfoHotelType.address = paramJSONObject.getString("address");
                localInfoHotelType.stars = ((float) paramJSONObject.getDouble("stars"));
                localInfoHotelType.distance = paramJSONObject.getDouble("distance");
                localInfoHotelType.image = paramJSONObject.getString("image");
                localInfoHotelType.suites_availability = paramJSONObject.getString("suites_availability");
                localInfoHotelType.lat =paramJSONObject.getDouble("lat");
                localInfoHotelType.lon = paramJSONObject.getDouble("lon");
                return localInfoHotelType;
            } catch (JSONException localJSONException) {
                localJSONException.printStackTrace();
                Log.e("JsonParser", "Error json parse");
                Toast.makeText(paramContext, paramContext.getResources().getString(R.string.error_json_parse), Toast.LENGTH_LONG).show();

            }
            Log.e("JsonParser", "Error load json");
            Toast.makeText(paramContext, paramContext.getResources().getString(R.string.error_json_load), Toast.LENGTH_LONG).show();
        }
        return null;
    }


    public ArrayList<ListHotelsType> jsonParserList(Context paramContext, JSONArray paramJSONArray) {
        ArrayList<ListHotelsType> localArrayList = new ArrayList<ListHotelsType>();

        if (paramJSONArray!=null) {
            try {
                for (int i = 0; i < paramJSONArray.length(); i++) {
                    ListHotelsType localListHotelsType = new ListHotelsType();
                    localListHotelsType.id = paramJSONArray.getJSONObject(i).getInt("id");
                    localListHotelsType.name = paramJSONArray.getJSONObject(i).getString("name");
                    localListHotelsType.address = paramJSONArray.getJSONObject(i).getString("address");
                    localListHotelsType.stars = ((float) paramJSONArray.getJSONObject(i).getDouble("stars"));
                    localListHotelsType.distance = paramJSONArray.getJSONObject(i).getDouble("distance");
                    localListHotelsType.suites_availability = paramJSONArray.getJSONObject(i).getString("suites_availability");

                    localListHotelsType.numb_free_places = new CountRoomsAvailable().countRoomsAvailable(localListHotelsType.suites_availability);
                    localArrayList.add(i, localListHotelsType);

                }
                return localArrayList;
            }catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(paramContext, paramContext.getResources().getString(R.string.error_json_parse), Toast.LENGTH_LONG).show();
            }
        }

        Log.e("JsonParser", "Error load json");
        Toast.makeText(paramContext, paramContext.getResources().getString(R.string.error_json_load), Toast.LENGTH_LONG).show();

        return null;
    }

}