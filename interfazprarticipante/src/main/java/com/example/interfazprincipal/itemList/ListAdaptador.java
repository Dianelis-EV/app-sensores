package com.example.interfazprincipal.itemList;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basedatos.model.Person;
import com.example.interfazprincipal.R;
import com.example.interfazprincipal.TodosLosParticipantesActivity;

import java.util.ArrayList;

public class ListAdaptador extends BaseAdapter {
    private Context contex;
    private ArrayList<Person> person;
    SparseBooleanArray itemselect;

    public ListAdaptador(Context context, ArrayList<Person> persona) {
        this.contex = context;
        this.person = persona;
        itemselect = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return person.size();
    }

    @Override
    public Object getItem(int i) {
        return person.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Person persona = (Person) getItem(i);
        view = LayoutInflater.from(contex).inflate(R.layout.item,null);
        TextView textViewnombre = (TextView) view.findViewById(R.id.textViewName);
        TextView textViewci = (TextView) view.findViewById(R.id.textViewCarnet);
        TextView textViewtelefono = (TextView) view.findViewById(R.id.textViewTelefonol);
        ImageView foto = (ImageView) view.findViewById(R.id.imageViewParticipante);

        CharSequence nombre = persona.getName();
        CharSequence ci = persona.getCi();
        CharSequence telefono = persona.getTelefono();
        textViewnombre.setText(nombre);
        textViewci.setText(ci);
        textViewtelefono.setText(telefono);
        foto.setVisibility(View.VISIBLE);

        return view;
    }

    //--------------------------------------------------------------------------------------
    public void eliminarItem(int position){
        person.remove(position);
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
//-------------------------------------------------------------------------------------


}
