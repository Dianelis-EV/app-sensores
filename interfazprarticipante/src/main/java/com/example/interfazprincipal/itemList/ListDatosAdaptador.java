package com.example.interfazprincipal.itemList;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basedatos.model.dataPerson;
import com.example.interfazprincipal.R;

import java.util.ArrayList;

public class ListDatosAdaptador extends BaseAdapter {
    private Context contex;
    private ArrayList<dataPerson> datos;
    SparseBooleanArray itemselect;


    public ListDatosAdaptador(Context context, ArrayList<dataPerson> datos) {
        this.contex = context;
        this.datos = datos;
        itemselect = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return datos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        dataPerson datos = (dataPerson) getItem(i);
        view = LayoutInflater.from(contex).inflate(R.layout.item,null);
        TextView textViewnombre = (TextView) view.findViewById(R.id.textViewName);
        TextView textViewci = (TextView) view.findViewById(R.id.textViewCarnet);
        TextView textViewtelefono = (TextView) view.findViewById(R.id.textViewTelefonol);
        ImageView foto = (ImageView) view.findViewById(R.id.imageViewParticipante);

        CharSequence nombre = datos.getDate();
        CharSequence ci = datos.getHour();
        CharSequence patologia = datos.getObservation();
        CharSequence telefono = "Patologia : " + patologia;
        textViewnombre.setText(nombre);
        textViewci.setText(ci);
        textViewtelefono.setText(telefono);
        foto.setVisibility(View.VISIBLE);


        return view;
    }

    //--------------------------------------------------------------------------------------
    public void eliminarItem(int position){
        datos.remove(position);
    }

    public void toggleSelection(int position){

        selectView(position, !itemselect.get(position));
    }

    public void eliminarSelecion(){
        itemselect = new SparseBooleanArray();
        notifyDataSetChanged();
    }
    public void selectView(int position, boolean value){
        if (value){
            itemselect.put(position, value);
        }else{
            itemselect.delete(position);
        }
    }

    public SparseBooleanArray getItemselect(){
        return itemselect;
    }
}
