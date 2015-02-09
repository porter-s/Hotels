package ru.example.user.hotels;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import ru.example.user.hotels.abstracts.ObjectData;
import ru.example.user.hotels.abstracts.ObjectDataCreator;
import ru.example.user.hotels.adapters.AdapterListViewHotels;
import ru.example.user.hotels.creators.DataHotelCreator;
import ru.example.user.hotels.methods.SortingData;
import ru.example.user.hotels.types.ListHotelsType;
import ru.example.user.hotels.util.Constants;


public class ListHotelsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotels);

        ObjectDataCreator dataHotel = new DataHotelCreator();
        final ObjectData listData = dataHotel.create("list");

        final ListView lvHotels = (ListView)findViewById(R.id.alh_lvHotels);
        final ProgressBar pbLoadHotels =(ProgressBar) findViewById(R.id.alh_pbLoadHotels);
        final RadioButton rbSortNumb = (RadioButton) findViewById(R.id.alh_rbSortNumb);
        final RadioButton rbSortDist = (RadioButton) findViewById(R.id.alh_rbSortDist);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                listData.loadData(getApplicationContext(), Constants.URL_HOTELS);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                ArrayList<ListHotelsType> buf = (ArrayList<ListHotelsType>) listData.getData();
                if(buf!=null){
                    lvHotels.setAdapter(new AdapterListViewHotels(getApplicationContext(), buf));
                }else{
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.error_message_load_dada), Toast.LENGTH_LONG).show();
                }

                pbLoadHotels.setVisibility(View.GONE);

            }
        }.execute();

        rbSortNumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ListHotelsType> buf = (ArrayList<ListHotelsType>) listData.getData();
                if (buf!=null){
                    pbLoadHotels.setVisibility(View.VISIBLE);
                    buf=new SortingData().mergeSortNumb(buf);
                    lvHotels.setAdapter(new AdapterListViewHotels(getApplicationContext(), buf));
                    pbLoadHotels.setVisibility(View.GONE);
                }

                else {
                    rbSortNumb.setChecked(false);
                    Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.message_load_dada),Toast.LENGTH_LONG).show();
                }
            }
        });

        rbSortDist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ListHotelsType> buf = (ArrayList<ListHotelsType>) listData.getData();
                if (buf!=null){
                    pbLoadHotels.setVisibility(View.VISIBLE);
                    buf=new SortingData().mergeSortDist(buf);
                    lvHotels.setAdapter(new AdapterListViewHotels(getApplicationContext(), buf));
                    pbLoadHotels.setVisibility(View.GONE);
                }

                else {
                    rbSortDist.setChecked(false);
                    Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.message_load_dada),Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_hotels, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
