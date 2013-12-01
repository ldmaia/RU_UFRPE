package br.ufrpe.ruufrpe;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TabHost tabsAlmoco;
	private TabHost tabsJantar;
	private TabHost tabsAJ;
	String abc;
	
	ArrayList<String> segundaAlmoco = new ArrayList<String>();
	ArrayList<String> tercaAlmoco = new ArrayList<String>();
	ArrayList<String> quartaAlmoco = new ArrayList<String>();
	ArrayList<String> quintaAlmoco = new ArrayList<String>();
	ArrayList<String> sextaAlmoco = new ArrayList<String>();
	
	ArrayList<String> segundaJantar = new ArrayList<String>();
	ArrayList<String> tercaJantar = new ArrayList<String>();
	ArrayList<String> quartaJantar = new ArrayList<String>();
	ArrayList<String> quintaJantar = new ArrayList<String>();
	ArrayList<String> sextaJantar = new ArrayList<String>();
	
	
	
	
	

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        ArrayAdapter<String> teste = new ArrayAdapter<String>(this,R.layout.row, quartaAlmoco);
        
        ListView a = (ListView) findViewById(R.id.listQuartaAlmoco);
        
        a.setAdapter(teste);
        
        tabsAJ = (TabHost) findViewById(R.id.tabhostJA);
        
        tabsAJ.setup();
        
        tabsAJ.addTab(tabsAJ.newTabSpec("tab1").setIndicator("Almoço").setContent(R.id.Almoco));
        tabsAJ.addTab(tabsAJ.newTabSpec("tab2").setIndicator("Jantar").setContent(R.id.Jantar));
        
        
        tabsAlmoco = (TabHost) findViewById(R.id.tabhostAlmoco);
        
        
        tabsAlmoco.setup();
        
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab1").setIndicator("Segunda").setContent(R.id.listSegundaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab2").setIndicator("Terça").setContent(R.id.listTercaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab3").setIndicator("Quarta").setContent(R.id.listQuartaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab4").setIndicator("Quinta").setContent(R.id.listQuintaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab5").setIndicator("Sexta").setContent(R.id.listSextaAlmoco));
        
        tabsJantar = (TabHost) findViewById(R.id.tabhostJantar);
        
        tabsJantar.setup();
        
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab1").setIndicator("Segunda").setContent(R.id.listSegundaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab2").setIndicator("Terça").setContent(R.id.listTercaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab3").setIndicator("Quarta").setContent(R.id.listQuartaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab4").setIndicator("Quinta").setContent(R.id.listQuintaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab5").setIndicator("Sexta").setContent(R.id.listSextaJantar));
        
        
        loadFromFile();
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    public void onClick(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute();

      }
    
    public void loadFromFile(){
    	
    	try{
    		JSONArray objects = new JSONArray(readFromFile());
    	
    		for (int i = 0; i < objects.length(); i++) {
			
				
			
				JSONObject session = objects.getJSONObject(i);
				
				segundaAlmoco.add(session.getString("segunda"));
				tercaAlmoco.add(session.getString("terca"));
				quartaAlmoco.add(session.getString("quarta"));
				quintaAlmoco.add(session.getString("quinta"));
				sextaAlmoco.add(session.getString("sexta"));
				
				segundaJantar.add(session.getString("segundaj"));
				tercaJantar.add(session.getString("tercaj"));
				quartaJantar.add(session.getString("quartaj"));
				quintaJantar.add(session.getString("quintaj"));
				sextaJantar.add(session.getString("sextaj"));
				
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
    	prefEditor.commit();
    	//prefEditor.apply();
    }
    
    public void clear() {
    	SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
    	SharedPreferences.Editor prefEditor = sharedPref.edit();
    	prefEditor.clear();
    	prefEditor.commit();
    }
    
    private class DownloadWebPageTask extends AsyncTask<Void, Void, JSONArray> {
    	
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
    				
                
    				segundaAlmoco.add(session.getString("segunda"));
    				tercaAlmoco.add(session.getString("terca"));
    				quartaAlmoco.add(session.getString("quarta"));
    				quintaAlmoco.add(session.getString("quinta"));
    				sextaAlmoco.add(session.getString("sexta"));
    				
    				segundaJantar.add(session.getString("segundaj"));
    				tercaJantar.add(session.getString("tercaj"));
    				quartaJantar.add(session.getString("quartaj"));
    				quintaJantar.add(session.getString("quintaj"));
    				sextaJantar.add(session.getString("sextaj"));
    			}
    			
    			catch(Exception e){
    				e.printStackTrace();
    			}
        }
    		
    		/*@Override
    		public void onTabChanged(String id) {
    			int t = tabsAlmoco.getCurrentTab();
    			HorizontalScrollView a = (HorizontalScrollView) findViewById(R.id.scroller);
    		    this.hScrollView.scrollTo(((int)(mTabHost.getWidth() * (position / ((double)(4 - 1))))), 0);
    			
    			
    		}*/
      }
    }
}
    
    
    	
   

