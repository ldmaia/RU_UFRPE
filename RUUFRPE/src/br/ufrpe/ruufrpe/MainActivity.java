package br.ufrpe.ruufrpe;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") public class MainActivity extends Activity {
	
	private TabHost tabsAlmoco;
	private TabHost tabsJantar;
	private TabHost tabsAJ;
	String abc;
	
	
	
	Integer[] segundaAlmocoTextIds = {R.id.segundaAlmoco01, R.id.segundaAlmoco02, R.id.segundaAlmoco03,
			R.id.segundaAlmoco04, R.id.segundaAlmoco05, R.id.segundaAlmoco06, R.id.segundaAlmoco07,
			R.id.segundaAlmoco08, R.id.segundaAlmoco09, R.id.segundaAlmoco10};
	
	Integer[] tercaAlmocoTextIds = {R.id.tercaAlmoco01, R.id.tercaAlmoco02, R.id.tercaAlmoco03,
			R.id.tercaAlmoco04, R.id.tercaAlmoco05, R.id.tercaAlmoco06, R.id.tercaAlmoco07,
			R.id.tercaAlmoco08, R.id.tercaAlmoco09, R.id.tercaAlmoco10};
	
	Integer[] quartaAlmocoTextIds = {R.id.quartaAlmoco01, R.id.quartaAlmoco02, R.id.quartaAlmoco03,
			R.id.quartaAlmoco04, R.id.quartaAlmoco05, R.id.quartaAlmoco06, R.id.quartaAlmoco07,
			R.id.quartaAlmoco08, R.id.quartaAlmoco09, R.id.quartaAlmoco10};
	
	Integer[] quintaAlmocoTextIds = {R.id.quintaAlmoco01, R.id.quintaAlmoco02, R.id.quintaAlmoco03,
			R.id.quintaAlmoco04, R.id.quintaAlmoco05, R.id.quintaAlmoco06, R.id.quintaAlmoco07,
			R.id.quintaAlmoco08, R.id.quintaAlmoco09, R.id.quintaAlmoco10};
	
	Integer[] sextaAlmocoTextIds = {R.id.sextaAlmoco01, R.id.sextaAlmoco02, R.id.sextaAlmoco03,
			R.id.sextaAlmoco04, R.id.sextaAlmoco05, R.id.sextaAlmoco06, R.id.sextaAlmoco07,
			R.id.sextaAlmoco08, R.id.sextaAlmoco09, R.id.sextaAlmoco10};
	
	
	
	Integer[] segundaJantarTextIds = {R.id.segundaJantar01, R.id.segundaJantar02, R.id.segundaJantar03,
			R.id.segundaJantar04, R.id.segundaJantar05, R.id.segundaJantar06, R.id.segundaJantar07,
			R.id.segundaJantar08, R.id.segundaJantar09, R.id.segundaJantar10};
	
	Integer[] tercaJantarTextIds = {R.id.tercaJantar01, R.id.tercaJantar02, R.id.tercaJantar03,
			R.id.tercaJantar04, R.id.tercaJantar05, R.id.tercaJantar06, R.id.tercaJantar07,
			R.id.tercaJantar08, R.id.tercaJantar09, R.id.tercaJantar10};
	
	Integer[] quartaJantarTextIds = {R.id.quartaJantar01, R.id.quartaJantar02, R.id.quartaJantar03,
			R.id.quartaJantar04, R.id.quartaJantar05, R.id.quartaJantar06, R.id.quartaJantar07,
			R.id.quartaJantar08, R.id.quartaJantar09, R.id.quartaJantar10};
	
	Integer[] quintaJantarTextIds = {R.id.quintaJantar01, R.id.quintaJantar02, R.id.quintaJantar03,
			R.id.quintaJantar04, R.id.quintaJantar05, R.id.quintaJantar06, R.id.quintaJantar07,
			R.id.quintaJantar08, R.id.quintaJantar09, R.id.quintaJantar10};
	
	Integer[] sextaJantarTextIds = {R.id.sextaJantar01, R.id.sextaJantar02, R.id.sextaJantar03,
			R.id.sextaJantar04, R.id.sextaJantar05, R.id.sextaJantar06, R.id.sextaJantar07,
			R.id.sextaJantar08, R.id.sextaJantar09, R.id.sextaJantar10};
	
	
	

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        // Just for testing, allow network access in the main thread
        // NEVER use this is productive code
        StrictMode.ThreadPolicy policy = new StrictMode.
        ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
        
        setContentView(R.layout.activity_main);
        
        abc = "hello";
        
tabsAJ = (TabHost) findViewById(R.id.tabhostJA);
        
        tabsAJ.setup();
        
        tabsAJ.addTab(tabsAJ.newTabSpec("tab1").setIndicator("Almoço").setContent(R.id.Almoco));
        tabsAJ.addTab(tabsAJ.newTabSpec("tab2").setIndicator("Jantar").setContent(R.id.Jantar));
        
        
        tabsAlmoco = (TabHost) findViewById(R.id.tabhostAlmoco);
        
        
        tabsAlmoco.setup();
        
        TextView test = new TextView(this);
        
        test.setText("Segun");
        
        
        
        
        
        
        
        
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab1").setIndicator("Segundaaaaaaaaaaaaaaaaaaaaaaaa").setContent(R.id.segundaAlmocoTab01));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab2").setIndicator("Terça").setContent(R.id.tercaAlmocoTab02));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab3").setIndicator("Quarta").setContent(R.id.quartaAlmocoTab03));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab4").setIndicator("Quinta").setContent(R.id.quintaAlmocoTab04));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab5").setIndicator("Sexta").setContent(R.id.sextaAlmocoTab05));
        
        //TextView legall = (TextView) tabsAlmoco.getChildAt(2);
        
        //legall.setText("neww");
        
        tabsJantar = (TabHost) findViewById(R.id.tabhostJantar);
        
        tabsJantar.setup();
        
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab1").setIndicator("Segunda").setContent(R.id.segundaJantarTab01));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab2").setIndicator("Terça").setContent(R.id.tercaJantarTab02));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab3").setIndicator("Quarta").setContent(R.id.quartaJantarTab03));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab4").setIndicator("Quinta").setContent(R.id.quintaJantarTab04));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab5").setIndicator("Sexta").setContent(R.id.sextaJantarTab05));
        
        
        loadFromFile();
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /*public void start2(View view) {
    	//TextView txt = (TextView) findViewById(R.id.TextView06);
    	
    	Integer[] mTextIds = {R.id.textView1, R.id.TextView02, R.id.TextView04,
    			R.id.TextView06, R.id.TextView08, R.id.TextView10, R.id.TextView12,
    			R.id.TextView14, R.id.TextView16, R.id.TextView18};

    	
    	try {
            HttpClient hc = new DefaultHttpClient();
            String URL = "https://script.google.com/macros/s/AKfycbzrQ9vx_alQ5yEvSFx4uMOURVNeJPKimn30UTp0PNYWIPA6_mQ/exec";
            HttpGet get = new HttpGet(URL);
            HttpResponse rp = hc.execute(get);
            if(rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                    String result = EntityUtils.toString(rp.getEntity());
                    JSONArray objects = new JSONArray(result);

                    for (int i = 0; i < objects.length(); i++) {
                            JSONObject session = objects.getJSONObject(i);
                            TextView txt = (TextView) findViewById(mTextIds[i]);
                            
                            txt.setText(session.getString("segunda"));
                            Item item = new Item();
                            item.name = session.getString("item");
                            item.count = session.getString("quantity");
                            items.add(item);
                    }
            }
    } catch (Exception e) {
            Log.e("ItemFeed", "Error loading JSON", e);
    }
    	
    	//Update task = new Update();
    	
    	//task.execute();
    }*/
    
    /*public void start3(View view){
//TextView txt = (TextView) findViewById(R.id.TextView06);
    	
    	TextView[] mTextView = {(TextView) findViewById(R.id.segundaAlmoco01), 
    			(TextView) findViewById(R.id.Segunda02),
    			(TextView) findViewById(R.id.Segunda03), (TextView) findViewById(R.id.Segunda04), (TextView) findViewById(R.id.Segunda05), (TextView) findViewById(R.id.segundaAlmoco06),
    			(TextView) findViewById(R.id.segundaAlmoco07), (TextView) findViewById(R.id.segundaAlmoco08), (TextView) findViewById(R.id.segundaAlmoco09), (TextView) findViewById(R.id.segundaAlmoco10)};
    	
    	Update task = new Update();
    	
    	task.execute(mTextView);
    	
    	
    }*/
    
    public void onClick(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute();

      }
    
    public void loadFromFile(){
    	
    	try{
    		JSONArray objects = new JSONArray(readFromFile());
    	
    		for (int i = 0; i < objects.length(); i++) {
			
				
			
				JSONObject session = objects.getJSONObject(i);
            
				TextView segundaAlmocoTxt = (TextView) findViewById(segundaAlmocoTextIds[i]);
				segundaAlmocoTxt.setText(session.getString("segunda"));
				
				TextView tercaAlmocoTxt = (TextView) findViewById(tercaAlmocoTextIds[i]);
				tercaAlmocoTxt.setText(session.getString("terca"));
				
				TextView quartaAlmocoTxt = (TextView) findViewById(quartaAlmocoTextIds[i]);
				quartaAlmocoTxt.setText(session.getString("quarta"));
				
				TextView quintaAlmocoTxt = (TextView) findViewById(quintaAlmocoTextIds[i]);
				quintaAlmocoTxt.setText(session.getString("quinta"));
				
				TextView sextaAlmocoTxt = (TextView) findViewById(sextaAlmocoTextIds[i]);
				sextaAlmocoTxt.setText(session.getString("sexta"));
				
				TextView segundaJantarTxt = (TextView) findViewById(segundaJantarTextIds[i]);
				segundaJantarTxt.setText(session.getString("segundaj"));
				
				TextView tercaJantarTxt = (TextView) findViewById(tercaJantarTextIds[i]);
				tercaJantarTxt.setText(session.getString("tercaj"));
				
				TextView quartaJantarTxt = (TextView) findViewById(quartaJantarTextIds[i]);
				quartaJantarTxt.setText(session.getString("quartaj"));
				
				TextView quintaJantarTxt = (TextView) findViewById(quintaJantarTextIds[i]);
				quintaJantarTxt.setText(session.getString("quintaj"));
				
				TextView sextaJantarTxt = (TextView) findViewById(sextaJantarTextIds[i]);
				sextaJantarTxt.setText(session.getString("sextaj"));
			}
    	}
			catch(Exception e){
				e.printStackTrace();
			}
    	}
    
    public String readFromFile(){
    	String result;
    	SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
        result = sharedPref.getString("jsonArray", "Erro");
        return result;
    }
    
    public void writeToFile(String json){
    	SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
    	SharedPreferences.Editor prefEditor = sharedPref.edit();
    	prefEditor.putString( "jsonArray", json );
    	prefEditor.apply();
    }
    
    public void clear() {
    	SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
    	SharedPreferences.Editor prefEditor = sharedPref.edit();
    	prefEditor.clear();
    	prefEditor.apply();
    }
    
    private class DownloadWebPageTask extends AsyncTask<Void, Void, JSONArray> {
    	
    	Integer[] segundaAlmocoTextIds = {R.id.segundaAlmoco01, R.id.segundaAlmoco02, R.id.segundaAlmoco03,
    			R.id.segundaAlmoco04, R.id.segundaAlmoco05, R.id.segundaAlmoco06, R.id.segundaAlmoco07,
    			R.id.segundaAlmoco08, R.id.segundaAlmoco09, R.id.segundaAlmoco10};
    	
    	Integer[] tercaAlmocoTextIds = {R.id.tercaAlmoco01, R.id.tercaAlmoco02, R.id.tercaAlmoco03,
    			R.id.tercaAlmoco04, R.id.tercaAlmoco05, R.id.tercaAlmoco06, R.id.tercaAlmoco07,
    			R.id.tercaAlmoco08, R.id.tercaAlmoco09, R.id.tercaAlmoco10};
    	
    	Integer[] quartaAlmocoTextIds = {R.id.quartaAlmoco01, R.id.quartaAlmoco02, R.id.quartaAlmoco03,
    			R.id.quartaAlmoco04, R.id.quartaAlmoco05, R.id.quartaAlmoco06, R.id.quartaAlmoco07,
    			R.id.quartaAlmoco08, R.id.quartaAlmoco09, R.id.quartaAlmoco10};
    	
    	Integer[] quintaAlmocoTextIds = {R.id.quintaAlmoco01, R.id.quintaAlmoco02, R.id.quintaAlmoco03,
    			R.id.quintaAlmoco04, R.id.quintaAlmoco05, R.id.quintaAlmoco06, R.id.quintaAlmoco07,
    			R.id.quintaAlmoco08, R.id.quintaAlmoco09, R.id.quintaAlmoco10};
    	
    	Integer[] sextaAlmocoTextIds = {R.id.sextaAlmoco01, R.id.sextaAlmoco02, R.id.sextaAlmoco03,
    			R.id.sextaAlmoco04, R.id.sextaAlmoco05, R.id.sextaAlmoco06, R.id.sextaAlmoco07,
    			R.id.sextaAlmoco08, R.id.sextaAlmoco09, R.id.sextaAlmoco10};
    	
    	
    	
    	Integer[] segundaJantarTextIds = {R.id.segundaJantar01, R.id.segundaJantar02, R.id.segundaJantar03,
    			R.id.segundaJantar04, R.id.segundaJantar05, R.id.segundaJantar06, R.id.segundaJantar07,
    			R.id.segundaJantar08, R.id.segundaJantar09, R.id.segundaJantar10};
    	
    	Integer[] tercaJantarTextIds = {R.id.tercaJantar01, R.id.tercaJantar02, R.id.tercaJantar03,
    			R.id.tercaJantar04, R.id.tercaJantar05, R.id.tercaJantar06, R.id.tercaJantar07,
    			R.id.tercaJantar08, R.id.tercaJantar09, R.id.tercaJantar10};
    	
    	Integer[] quartaJantarTextIds = {R.id.quartaJantar01, R.id.quartaJantar02, R.id.quartaJantar03,
    			R.id.quartaJantar04, R.id.quartaJantar05, R.id.quartaJantar06, R.id.quartaJantar07,
    			R.id.quartaJantar08, R.id.quartaJantar09, R.id.quartaJantar10};
    	
    	Integer[] quintaJantarTextIds = {R.id.quintaJantar01, R.id.quintaJantar02, R.id.quintaJantar03,
    			R.id.quintaJantar04, R.id.quintaJantar05, R.id.quintaJantar06, R.id.quintaJantar07,
    			R.id.quintaJantar08, R.id.quintaJantar09, R.id.quintaJantar10};
    	
    	Integer[] sextaJantarTextIds = {R.id.sextaJantar01, R.id.sextaJantar02, R.id.sextaJantar03,
    			R.id.sextaJantar04, R.id.sextaJantar05, R.id.sextaJantar06, R.id.sextaJantar07,
    			R.id.sextaJantar08, R.id.sextaJantar09, R.id.sextaJantar10};
    	
    	
    	
    	@Override
    	protected JSONArray doInBackground(Void... params) {
    		JSONArray objects = null;
    		
    		try{
    		
                HttpClient hc = new DefaultHttpClient();
                String URL = "https://script.google.com/macros/s/AKfycbzrQ9vx_alQ5yEvSFx4uMOURVNeJPKimn30UTp0PNYWIPA6_mQ/exec";
                HttpGet get = new HttpGet(URL);
                HttpResponse rp = hc.execute(get);
                if(rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                        String result = EntityUtils.toString(rp.getEntity());
                        objects = new JSONArray(result);
                        writeToFile(result);
                        
                                
                                
                               
                                
                                /*Item item = new Item();
                                item.name = session.getString("item");
                                item.count = session.getString("quantity");
                                items.add(item);*/
                        }
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
        return objects;
    		
    }
    	@Override
        protected void onPostExecute(JSONArray objects) {
    		for (int i = 0; i < objects.length(); i++) {
    			try{
    				
    			
    				JSONObject session = objects.getJSONObject(i);
                
    				TextView segundaAlmocoTxt = (TextView) findViewById(segundaAlmocoTextIds[i]);
    				segundaAlmocoTxt.setText(session.getString("segunda"));
    				
    				TextView tercaAlmocoTxt = (TextView) findViewById(tercaAlmocoTextIds[i]);
    				tercaAlmocoTxt.setText(session.getString("terca"));
    				
    				TextView quartaAlmocoTxt = (TextView) findViewById(quartaAlmocoTextIds[i]);
    				quartaAlmocoTxt.setText(session.getString("quarta"));
    				
    				TextView quintaAlmocoTxt = (TextView) findViewById(quintaAlmocoTextIds[i]);
    				quintaAlmocoTxt.setText(session.getString("quinta"));
    				
    				TextView sextaAlmocoTxt = (TextView) findViewById(sextaAlmocoTextIds[i]);
    				sextaAlmocoTxt.setText(session.getString("sexta"));
    				
    				TextView segundaJantarTxt = (TextView) findViewById(segundaJantarTextIds[i]);
    				segundaJantarTxt.setText(session.getString("segundaj"));
    				
    				TextView tercaJantarTxt = (TextView) findViewById(tercaJantarTextIds[i]);
    				tercaJantarTxt.setText(session.getString("tercaj"));
    				
    				TextView quartaJantarTxt = (TextView) findViewById(quartaJantarTextIds[i]);
    				quartaJantarTxt.setText(session.getString("quartaj"));
    				
    				TextView quintaJantarTxt = (TextView) findViewById(quintaJantarTextIds[i]);
    				quintaJantarTxt.setText(session.getString("quintaj"));
    				
    				TextView sextaJantarTxt = (TextView) findViewById(sextaJantarTextIds[i]);
    				sextaJantarTxt.setText(session.getString("sextaj"));
    			}
    			
    			catch(Exception e){
    				e.printStackTrace();
    			}
        }
      }
    }
}
    
    
    	
   

