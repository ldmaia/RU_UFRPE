package br.ufrpe.ruufrpe;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<String>{
	
	private final Context context;
    private final ArrayList<String> pratos;
    private final ArrayList<String> nomes;

    public MyArrayAdapter(Context context, ArrayList<String> pratos, ArrayList<String> nomes) {
            super(context, R.layout.row, pratos);
            this.context = context;
            this.pratos = pratos;
            this.nomes  = nomes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row, parent, false);
            String i = pratos.get(position);
            String i2 = nomes.get(position);

            TextView teste = (TextView) rowView.findViewById(R.id.prato);
            TextView teste2 = (TextView) rowView.findViewById(R.id.nomePrato);

            teste.setText(i2);
            teste2.setText(i);

            return rowView;
    }

}
