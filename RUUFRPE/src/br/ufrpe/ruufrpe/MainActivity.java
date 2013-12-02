package br.ufrpe.ruufrpe;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTabChangeListener{
	
	private TabHost tabsAlmoco;
	private TabHost tabsJantar;
	private TabHost tabsAJ;
	
	ArrayList<String> nomesAlmoco = new ArrayList<String>();
	
	ArrayList<String> segundaAlmoco = new ArrayList<String>();
	ArrayList<String> tercaAlmoco = new ArrayList<String>();
	ArrayList<String> quartaAlmoco = new ArrayList<String>();
	ArrayList<String> quintaAlmoco = new ArrayList<String>();
	ArrayList<String> sextaAlmoco = new ArrayList<String>();
	
	ArrayList<String> nomesJantar = new ArrayList<String>();
	
	ArrayList<String> segundaJantar = new ArrayList<String>();
	ArrayList<String> tercaJantar = new ArrayList<String>();
	ArrayList<String> quartaJantar = new ArrayList<String>();
	ArrayList<String> quintaJantar = new ArrayList<String>();
	ArrayList<String> sextaJantar = new ArrayList<String>();
	
	
	
	
	

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        
        
        setAdapters();
        
        setTabs();
        
        loadFromFile();
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //MenuItem a = (MenuItem) findViewById(R.id.Atualizar);
        //a.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        		
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
            switch(item.getItemId()) {
            /*
             * Typically, an application registers automatically, so options
             * below are disabled. Uncomment them if you want to manually
             * register or unregister the device (you will also need to
             * uncomment the equivalent options on options_menu.xml).
             */

            case R.id.Atualizar:
                    
                    onClick();
                    return true;
            default:
                    return super.onOptionsItemSelected(item);
            }
    }
    
    
    public void setTabs() {
    	tabsAJ = (TabHost) findViewById(R.id.tabhostJA);
        
        tabsAJ.setup();
        
        tabsAJ.addTab(tabsAJ.newTabSpec("tab1").setIndicator("Almo�o").setContent(R.id.Almoco));
        tabsAJ.addTab(tabsAJ.newTabSpec("tab2").setIndicator("Jantar").setContent(R.id.Jantar));
        
        tabsAlmoco = (TabHost) findViewById(R.id.tabhostAlmoco);
        
        tabsAlmoco.setup();
        
        tabsAlmoco.setOnTabChangedListener(this);
        
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab1").setIndicator("Segunda").setContent(R.id.listSegundaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab2").setIndicator("Ter�a").setContent(R.id.listTercaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab3").setIndicator("Quarta").setContent(R.id.listQuartaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab4").setIndicator("Quinta").setContent(R.id.listQuintaAlmoco));
        tabsAlmoco.addTab(tabsAlmoco.newTabSpec("almocoTab5").setIndicator("Sexta").setContent(R.id.listSextaAlmoco));
        
        tabsJantar = (TabHost) findViewById(R.id.tabhostJantar);
        
        tabsJantar.setup();
        
        tabsJantar.setOnTabChangedListener(this);
        
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab1").setIndicator("Segunda").setContent(R.id.listSegundaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab2").setIndicator("Ter�a").setContent(R.id.listTercaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab3").setIndicator("Quarta").setContent(R.id.listQuartaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab4").setIndicator("Quinta").setContent(R.id.listQuintaJantar));
        tabsJantar.addTab(tabsJantar.newTabSpec("JantarTab5").setIndicator("Sexta").setContent(R.id.listSextaJantar));
    }
    
    public void setAdapters(){
    	
    	MyArrayAdapter adapterSegundaAlmoco = new MyArrayAdapter(this, segundaAlmoco, nomesAlmoco);
    	MyArrayAdapter adapterTercaAlmoco = new MyArrayAdapter(this, tercaAlmoco, nomesAlmoco);
    	MyArrayAdapter adapterQuartaAlmoco = new MyArrayAdapter(this, quartaAlmoco, nomesAlmoco);
    	MyArrayAdapter adapterQuintaAlmoco = new MyArrayAdapter(this, quintaAlmoco, nomesAlmoco);
    	MyArrayAdapter adapterSextaAlmoco = new MyArrayAdapter(this, sextaAlmoco, nomesAlmoco);
    	
    	MyArrayAdapter adapterSegundaJantar = new MyArrayAdapter(this, segundaJantar, nomesJantar);
    	MyArrayAdapter adapterTercaJantar = new MyArrayAdapter(this, tercaJantar, nomesJantar);
    	MyArrayAdapter adapterQuartaJantar = new MyArrayAdapter(this, quartaJantar, nomesJantar);
    	MyArrayAdapter adapterQuintaJantar = new MyArrayAdapter(this, quintaJantar, nomesJantar);
    	MyArrayAdapter adapterSextaJantar = new MyArrayAdapter(this, sextaJantar, nomesJantar);
        
        ListView listSegundaAlmoco = (ListView) findViewById(R.id.listSegundaAlmoco);
        ListView listTercaAlmoco = (ListView) findViewById(R.id.listTercaAlmoco);
        ListView listQuartaAlmoco = (ListView) findViewById(R.id.listQuartaAlmoco);
        ListView listQuintaAlmoco = (ListView) findViewById(R.id.listQuintaAlmoco);
        ListView listSextaAlmoco = (ListView) findViewById(R.id.listSextaAlmoco);
        
        ListView listSegundaJantar = (ListView) findViewById(R.id.listSegundaJantar);
        ListView listTercaJantar = (ListView) findViewById(R.id.listTercaJantar);
        ListView listQuartaJantar = (ListView) findViewById(R.id.listQuartaJantar);
        ListView listQuintaJantar = (ListView) findViewById(R.id.listQuintaJantar);
        ListView listSextaJantar = (ListView) findViewById(R.id.listSextaJantar);
        
        listSegundaAlmoco.setAdapter(adapterSegundaAlmoco);
        listTercaAlmoco.setAdapter(adapterTercaAlmoco);
        listQuartaAlmoco.setAdapter(adapterQuartaAlmoco);
        listQuintaAlmoco.setAdapter(adapterQuintaAlmoco);
        listSextaAlmoco.setAdapter(adapterSextaAlmoco);
        
        listSegundaJantar.setAdapter(adapterSegundaJantar);
        listTercaJantar.setAdapter(adapterTercaJantar);
        listQuartaJantar.setAdapter(adapterQuartaJantar);
        listQuintaJantar.setAdapter(adapterQuintaJantar);
        listSextaJantar.setAdapter(adapterSextaJantar);
    }
    
    public void clearArrays(){
    	
    	nomesAlmoco.clear();
		segundaAlmoco.clear();
		tercaAlmoco.clear();
		quartaAlmoco.clear();
		quintaAlmoco.clear();
		sextaAlmoco.clear();
		
		nomesJantar.clear();
		segundaJantar.clear();
		tercaJantar.clear();
		quartaJantar.clear();
		quintaJantar.clear();
		sextaJantar.clear();
    }
    
    @Override
	public void onTabChanged(String id) {
    	
    	if(tabsAJ.getCurrentTab() == 0){
    		int t = tabsAlmoco.getCurrentTab();
    		HorizontalScrollView a = (HorizontalScrollView) findViewById(R.id.scrollAlmoco);
    	    a.scrollTo((int) tabsAlmoco.getWidth()/10*t, 0);
    	}
    	else{
    		int t = tabsJantar.getCurrentTab();
    		HorizontalScrollView a = (HorizontalScrollView) findViewById(R.id.scrollJantar);
    	    a.scrollTo((int) tabsAlmoco.getWidth()/10*t, 0);
    	}
		
		
		
	}
    
    public void onClick() {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute();

      }
    
    public void loadFromFile(){
    	
    	try{
    		JSONArray objects = new JSONArray(readFromFile());
    	
    		for (int i = 0; i < objects.length(); i++) {
			
				
			
				JSONObject session = objects.getJSONObject(i);
				
				nomesAlmoco.add(session.getString("nomesalmoco"));
				segundaAlmoco.add(session.getString("segunda"));
				tercaAlmoco.add(session.getString("terca"));
				quartaAlmoco.add(session.getString("quarta"));
				quintaAlmoco.add(session.getString("quinta"));
				sextaAlmoco.add(session.getString("sexta"));
				
				nomesJantar.add(session.getString("nomesjantar"));
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
    	setAdapters();
    	}
    
    public String readFromFile(){
    	String result;
    	SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
        result = sharedPref.getString("jsonArray", "Erro");
        System.out.println(result);
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
    	protected final ProgressDialog a = new ProgressDialog(MainActivity.this);
    	
    	@Override
    	protected void onPreExecute(){
    		a.setMessage("Atualizando...");
            a.setCancelable(false);
            a.show();
    	}
    	
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
                        System.out.println(result);
                        
                        }
    		}
    		catch(UnknownHostException e){
    			e.printStackTrace();
    		}
    		
    		catch(Exception e){
    			e.printStackTrace();
    		}
        return objects;
    		
    }
    	@Override
        protected void onPostExecute(JSONArray objects) {
    		clearArrays();
    		for (int i = 0; i < objects.length(); i++) {
    			try{
    				
    			
    				JSONObject session = objects.getJSONObject(i);
    				
    				nomesAlmoco.add(session.getString("nomesalmoco"));
    				segundaAlmoco.add(session.getString("segunda"));
    				tercaAlmoco.add(session.getString("terca"));
    				quartaAlmoco.add(session.getString("quarta"));
    				quintaAlmoco.add(session.getString("quinta"));
    				sextaAlmoco.add(session.getString("sexta"));
    				
    				nomesJantar.add(session.getString("nomesjantar"));
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
    		setAdapters();
    		if (a.isShowing()) {
                a.dismiss();

            }
      }
    }
}
    
    
    	
   

