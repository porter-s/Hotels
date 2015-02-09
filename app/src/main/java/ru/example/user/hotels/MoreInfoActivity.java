package ru.example.user.hotels;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import ru.example.user.hotels.abstracts.ObjectData;
import ru.example.user.hotels.abstracts.ObjectDataCreator;
import ru.example.user.hotels.creators.DataHotelCreator;
import ru.example.user.hotels.types.InfoHotelType;
import ru.example.user.hotels.util.Constants;


public class MoreInfoActivity extends ActionBarActivity {

    private WebView webMap;
    AsyncTask atLoadData;
    AsyncTask atLoadDataImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        final TextView tName = (TextView) findViewById(R.id.ami_tName);
        final TextView tAddress = (TextView) findViewById(R.id.ami_tAddress);
        final TextView tDistance = (TextView) findViewById(R.id.ami_tDistance);
        final TextView tSuites_availability = (TextView) findViewById(R.id.ami_tSuites_availability);
        final TextView tLat = (TextView) findViewById(R.id.ami_tLat);
        final TextView tLon = (TextView) findViewById(R.id.ami_tLon);
        final RatingBar rbStars = (RatingBar) findViewById(R.id.ami_rbStars);
        final ImageView imageHotel = (ImageView) findViewById(R.id.ami_ImageHotel);
        final ProgressBar pbImageHotel =(ProgressBar) findViewById(R.id.ami_pbImageHotel);
        final ProgressBar pbLoadInfo =(ProgressBar) findViewById(R.id.ami_pbLoadInfo);

        webMap = (WebView) findViewById(R.id.ami_webMap);

        ObjectDataCreator dataHotel = new DataHotelCreator();
        final ObjectData infoData = dataHotel.create("info");

        ObjectDataCreator dataImage = new DataHotelCreator();
        final ObjectData imageData = dataImage.create("img");

        final String str_id = getIntent().getStringExtra("id");
        Log.d("MoreInfoAcrivity", "id="+str_id);

        webMap.getSettings().setJavaScriptEnabled(true);
        webMap.setFocusable(false);
        webMap.setFocusableInTouchMode(false);
        webMap.setBackgroundColor(0x00000000);

        this.webMap.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                webMap.setBackgroundColor(0x00000000);
                if (Build.VERSION.SDK_INT >= 11) webMap.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            }
        });

        atLoadData = new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                infoData.loadData(getApplicationContext(), Constants.URL_MORE_INFO+str_id+Constants.URL_PREFIX);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                InfoHotelType buf = (InfoHotelType) infoData.getData();

                if(buf!=null){
                    tName.setText(buf.name.toString());
                    tAddress.setText(buf.address.toString());
                    tDistance.setText(getApplicationContext().getResources().getString(R.string.distance)+buf.distance.toString()+" "+getApplicationContext().getResources().getString(R.string.metr));
                    tSuites_availability.setText(getApplicationContext().getResources().getString(R.string.suites_availability)+" "+buf.suites_availability.toString());
                    tLat.setText(getApplicationContext().getResources().getString(R.string.lat)+buf.lat.toString());
                    tLon.setText(getApplicationContext().getResources().getString(R.string.lon)+buf.lon.toString());
                    rbStars.setRating(buf.stars);

                    String coordinates;
                    coordinates=buf.lat.toString()+","+buf.lon.toString();

                    webMap.loadUrl(Constants.URL_MAP_1+coordinates+Constants.URL_MAP_2+coordinates+Constants.URL_MAP_3);

                    pbLoadInfo.setVisibility(View.GONE);

                    if(buf.image!=null){

                        atLoadDataImage =new AsyncTask<String,Void,Void>(){

                            @Override
                            protected Void doInBackground(String... params) {

                                imageData.loadData(getApplicationContext(), params[0]);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);

                                final Bitmap bmp =(Bitmap)imageData.getData();
                                imageHotel.setImageBitmap(bmp);

                                pbImageHotel.setVisibility(View.GONE);

                                imageHotel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(bmp!=null){
                                            Intent intent = new Intent(MoreInfoActivity.this, ImageFullScreanActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                            byte[] byteArray = stream.toByteArray();

                                            intent.putExtra("picture", byteArray);

                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.no_picture), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }.execute((Constants.URL_IMAGE+buf.image));

                    }else{
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.error_message_load_dada_image), Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.error_message_load_dada), Toast.LENGTH_LONG).show();
                }


            }
        }.execute();

    }


    @Override
    protected void onStop() {
        super.onStop();
        if(atLoadData==null) return;
        Log.d("MoreInfoActivity", "cancel loadData: " + atLoadData.cancel(false));

        if(atLoadData==null) return;
        Log.d("MoreInfoActivity", "cancel loadDataImage: " + atLoadData.cancel(false));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more_info, menu);
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
