package br.ufrpe.ruufrpe;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class Update extends AsyncTask<TextView, Void, TextView> {
	
	@Override
	protected TextView doInBackground(TextView... params) {
		
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
                            
                            params[i].setText(session.getString("segunda"));
                           
                            
                            /*Item item = new Item();
                            item.name = session.getString("item");
                            item.count = session.getString("quantity");
                            items.add(item);*/
                    }
            }
    } catch (Exception e) {
            Log.e("ItemFeed", "Error loading JSON", e);
    }
    return params[5];
		
		}
	@Override
    protected void onPostExecute(TextView result) {
      result.setText("teste");
    }
	}

	
