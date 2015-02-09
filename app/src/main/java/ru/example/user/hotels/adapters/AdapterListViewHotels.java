package ru.example.user.hotels.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import ru.example.user.hotels.HolderListViewItem;
import ru.example.user.hotels.types.ListHotelsType;
import ru.example.user.hotels.MoreInfoActivity;
import ru.example.user.hotels.R;

/**
 * Created by user on 29.01.15.
 */
public class AdapterListViewHotels extends BaseAdapter
{
    LayoutInflater inflater;
    ArrayList<ListHotelsType> listHotels;
    Context mContext;

    public AdapterListViewHotels(Context paramContext, ArrayList<ListHotelsType> paramArrayList)
    {
        this.mContext = paramContext;
        this.listHotels = paramArrayList;
        this.inflater = ((LayoutInflater)this.mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE));
    }

    public int getCount()
    {
        return this.listHotels.size();
    }

    public Object getItem(int paramInt)
    {
        return this.listHotels.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {

        HolderListViewItem holder;

        if (paramView == null)
        {
            paramView = this.inflater.inflate(R.layout.lvh_item, paramViewGroup, false);
            holder = new HolderListViewItem();

            holder.tName = (TextView) paramView.findViewById(R.id.lvh_item_tName);
            holder.tAddress = (TextView)paramView.findViewById(R.id.lvh_item_tAddress);
            holder.tDistance = (TextView)paramView.findViewById(R.id.lvh_item_tDistance);
            holder.tNumbFreePlaces = (TextView)paramView.findViewById(R.id.lvh_item_tNumbFreePlaces);
            holder.rbStars = (RatingBar) paramView.findViewById(R.id.lvh_item_rbStars);
            paramView.setTag(holder);
        }else{
            holder = (HolderListViewItem) paramView.getTag();
        }

        holder.tName.setText(listHotels.get(paramInt).name);
        holder.tAddress.setText(listHotels.get(paramInt).address);
        holder.tDistance.setText(mContext.getResources().getString(R.string.distance)+listHotels.get(paramInt).distance.toString()+" "+mContext.getResources().getString(R.string.metr));
        holder.tNumbFreePlaces.setText(mContext.getResources().getString(R.string.numb_free_places)+listHotels.get(paramInt).numb_free_places);
        holder.rbStars.setRating(listHotels.get(paramInt).stars);

        paramView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoreInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",Integer.toString(listHotels.get(paramInt).id));
                mContext.startActivity(intent);
            }
        });

        return paramView;

    }
}