package ru.example.user.hotels.creators;

import ru.example.user.hotels.abstracts.ObjectData;
import ru.example.user.hotels.abstracts.ObjectDataCreator;
import ru.example.user.hotels.objects.ObjectImg;
import ru.example.user.hotels.objects.ObjectInfo;
import ru.example.user.hotels.objects.ObjectList;

/**
 * Created by user on 29.01.15.
 */
public class DataHotelCreator extends ObjectDataCreator
{
    public ObjectData create(String tag)
    {
        if (tag.equalsIgnoreCase("list"))
        {
            ObjectList localObjectList = new ObjectList();
            return localObjectList;
        }
        if (tag.equalsIgnoreCase("info"))
        {
            ObjectInfo localObjectInfo = new ObjectInfo();
            return localObjectInfo;
        }
        if (tag.equalsIgnoreCase("img"))
        {
            ObjectImg localObjectImg = new ObjectImg();
            return localObjectImg;
        }
        return null;
    }
}
