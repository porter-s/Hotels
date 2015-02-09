package ru.example.user.hotels.methods;

import java.util.ArrayList;

import ru.example.user.hotels.types.ListHotelsType;

/**
 * Created by user on 29.01.15.
 */

public class SortingData
{

    public static ArrayList<ListHotelsType> mergeSortNumb(ArrayList<ListHotelsType> paramArrayList) {

        int left, right, medium;
        ArrayList<ListHotelsType> localArrayList = new ArrayList<ListHotelsType>();

        left = 0;
        medium = Math.round(paramArrayList.size() / 2);
        right = medium;

        for (int p = left; p < right; p++) {
            for (int i = p + 1; i < right; i++) {

                if (paramArrayList.get(i).numb_free_places > paramArrayList.get(p).numb_free_places) {
                    ListHotelsType buf = new ListHotelsType();
                    buf=paramArrayList.get(i);
                    paramArrayList.set(i,paramArrayList.get(p));
                    paramArrayList.set(p,buf);
                }
            }
        }

        for (int p = right; p < paramArrayList.size(); p++) {
            for (int i = p + 1; i < paramArrayList.size(); i++) {

                if (paramArrayList.get(i).numb_free_places > paramArrayList.get(p).numb_free_places) {
                    ListHotelsType buf = new ListHotelsType();
                    buf=paramArrayList.get(i);
                    paramArrayList.set(i,paramArrayList.get(p));
                    paramArrayList.set(p,buf);
                }

            }
        }

        left = 0;
        right = medium;

        for (int i = 0; i < paramArrayList.size(); i++) {
            if (left >= medium) {
                localArrayList.add(i, paramArrayList.get(right));
                right++;
                continue;
            }
            if (right > paramArrayList.size()-1) {
                localArrayList.add(i, paramArrayList.get(left));
                left++;
                continue;
            }
            if (paramArrayList.get(left).numb_free_places>paramArrayList.get(right).numb_free_places) {
                localArrayList.add(i, paramArrayList.get(left));
                left++;
            } else {
                localArrayList.add(i, paramArrayList.get(right));
                right++;
            }
        }
        return localArrayList;
    }

    public static ArrayList<ListHotelsType> mergeSortDist(ArrayList<ListHotelsType> paramArrayList) {

        int left, right, medium;
        ArrayList<ListHotelsType> localArrayList = new ArrayList<ListHotelsType>();

        left = 0;
        medium = Math.round(paramArrayList.size() / 2);
        right = medium;

        for (int p = left; p < right; p++) {
            for (int i = p + 1; i < right; i++) {

                if (paramArrayList.get(i).distance < paramArrayList.get(p).distance) {
                    ListHotelsType buf = new ListHotelsType();
                    buf=paramArrayList.get(i);
                    paramArrayList.set(i,paramArrayList.get(p));
                    paramArrayList.set(p,buf);
                }
            }
        }

        for (int p = right; p < paramArrayList.size(); p++) {
            for (int i = p + 1; i < paramArrayList.size(); i++) {

                if (paramArrayList.get(i).distance < paramArrayList.get(p).distance) {
                    ListHotelsType buf = new ListHotelsType();
                    buf=paramArrayList.get(i);
                    paramArrayList.set(i,paramArrayList.get(p));
                    paramArrayList.set(p,buf);
                }

            }
        }

        left = 0;
        right = medium;

        for (int i = 0; i < paramArrayList.size(); i++) {
            if (left >= medium) {
                localArrayList.add(i, paramArrayList.get(right));
                right++;
                continue;
            }
            if (right > paramArrayList.size()-1) {
                localArrayList.add(i, paramArrayList.get(left));
                left++;
                continue;
            }
            if (paramArrayList.get(left).distance<paramArrayList.get(right).distance) {
                localArrayList.add(i, paramArrayList.get(left));
                left++;
            } else {
                localArrayList.add(i, paramArrayList.get(right));
                right++;
            }
        }
        return localArrayList;
    }
}