package com.example.interfazprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.basedatos.bd_manager;
import com.example.basedatos.model.Person;
import com.example.basedatos.model.dataPerson;
import com.example.interfazprincipal.itemList.ListDatosAdaptador;
import com.example.interfazprincipal.service.IAllUse;

import java.util.ArrayList;

public class DatosParticipantesActivity extends AppCompatActivity implements IAllUse {
    Object object;
    ArrayList<dataPerson> dataArrayList = new ArrayList<>();
    ListView listView;
    ListDatosAdaptador listAdaptador;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datosparticioantes);

        listView = (ListView) findViewById(R.id.lista_datos);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            person = (Person) bundle.get("persona");
        }

        atras_participante();
        actualizar_lista();
        nueva_insertar();

    }

    private void atras_participante(){
        ImageButton imageButton = (ImageButton) findViewById(R.id.atras_participante);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatosParticipantesActivity.this, TodosLosParticipantesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void actualizar_lista() {
        bd_manager manager = new bd_manager(this);
        ArrayList<dataPerson> datos= manager.data_list(person);
        listAdaptador = new ListDatosAdaptador(this,datos);
        listView.setAdapter(listAdaptador);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int poss;
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                poss = i;
                listAdaptador.toggleSelection(i);
                actionMode.setTitle(String.valueOf(listView.getCheckedItemCount()));
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.menu_delete_update,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.eliminar_menu){
                    SparseBooleanArray select = listAdaptador.getItemselect();
                    bd_manager manager = new bd_manager(DatosParticipantesActivity.this);
                    for(int i= (select.size()-1); i>=0; i--){
                        if(select.valueAt(i)){
                            object = listView.getAdapter().getItem(i);
                            if (object instanceof dataPerson){
                                dataPerson dato =(dataPerson) object;
                                manager.data_delete(person.getCi(),dato.getDate());
                                listAdaptador.eliminarItem(i);
                                Toast.makeText(DatosParticipantesActivity.this,"la cantidad seleccionada es: "+ datos.size(), Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                    listAdaptador.notifyDataSetChanged();
                    select.clear();
                    actionMode.finish();

                }else if(menuItem.getItemId() == R.id.modificar_menu){
                    SparseBooleanArray select = listAdaptador.getItemselect();
                    if(select.size() == 1){
                        object = listView.getAdapter().getItem(poss);
                        if (object instanceof dataPerson){
                            dataPerson datos =(dataPerson) object;

                            Intent intent = new Intent(DatosParticipantesActivity.this, ParticipanteActivity.class);
                            Bundle enviardatos = new Bundle();
                            enviardatos.putSerializable("datos",datos);
                            enviardatos.putSerializable("persona",person);
                            intent.putExtras(enviardatos);
                            startActivity(intent);
                            finish();
                        }
                        listAdaptador.notifyDataSetChanged();
                        select.clear();
                        actionMode.finish();

                    }else{
                        Toast.makeText(DatosParticipantesActivity.this, "Solo se puede modificar 1 participante a la ves",
                                Toast.LENGTH_LONG).show();
                    }

                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                listAdaptador.eliminarSelecion();

            }
        });
    }

    @Override
    public void nueva_insertar() {
        ImageView annadirdatos = (ImageView) findViewById(R.id.imageViewAnnadirDatos);

        annadirdatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatosParticipantesActivity.this, ParticipanteActivity.class);
                Bundle enviardatos = new Bundle();
                enviardatos.putSerializable("persona",person);
                intent.putExtras(enviardatos);
                intent.putExtra("clase", "Crear_datos");
                startActivity(intent);
                finish();
            }
        });

    }
}