package com.example.interfazprincipal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.basedatos.bd_manager;
import com.example.basedatos.model.Person;
import com.example.interfazprincipal.itemList.ListAdaptador;
import com.example.interfazprincipal.service.IAllUse;

import java.util.ArrayList;

public class TodosLosParticipantesActivity extends AppCompatActivity implements IAllUse {
    Object object;
    ArrayList<Person> personArrayList = new ArrayList<>();
    ListView listView;
    ListAdaptador listAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_los_participantes);
        listView = (ListView) findViewById(R.id.lista_participantes);
        actualizar_lista();
        nueva_insertar();
        atras_principal();
    }

    @Override
    public void actualizar_lista(){

        bd_manager manager = new bd_manager(this);
        ArrayList<Person> persona= manager.person_list();
        listAdaptador = new ListAdaptador(this,persona);
        listView.setAdapter(listAdaptador);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
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
                    bd_manager manager = new bd_manager(TodosLosParticipantesActivity.this);
                    for(int i= (select.size()-1); i>=0; i--){
                        if(select.valueAt(i)){
                            object = listView.getAdapter().getItem(i);
                            if (object instanceof Person){
                                Person person =(Person) object;
                                manager.person_delete(person.getCi());
                                listAdaptador.eliminarItem(i);
                                Toast.makeText(TodosLosParticipantesActivity.this,"la cantidad seleccionada es: "+ persona.size(), Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                    listAdaptador.notifyDataSetChanged();
                    select.clear();
                    actionMode.finish();

                }else if(menuItem.getItemId() == R.id.modificar_menu){
                    SparseBooleanArray select = listAdaptador.getItemselect();
                    if(select.size() == 1){
                        for(int i= (select.size()-1); i>=0; i--){
                            if(select.valueAt(i)){
                                object = listView.getAdapter().getItem(i);
                                if (object instanceof Person){
                                    Person person =(Person) object;

                                    Intent intent = new Intent(TodosLosParticipantesActivity.this, ParticipanteActivity.class);
                                    Bundle enviardatos = new Bundle();
                                    enviardatos.putSerializable("persona",person);
                                    intent.putExtras(enviardatos);
                                    startActivity(intent);

                                    finish();
                                }

                            }
                        }
                        listAdaptador.notifyDataSetChanged();
                        select.clear();
                        actionMode.finish();

                    }else{
                        Toast.makeText(TodosLosParticipantesActivity.this, "Solo se puede modificar 1 participante a la ves",
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
    public void nueva_insertar(){
        ImageView insert= (ImageView) findViewById(R.id.imageViewAnnadir);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodosLosParticipantesActivity.this, ParticipanteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void atras_principal(){
        ImageButton imageButton = (ImageButton) findViewById(R.id.atras_principal);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.caminata.PrincipalActivity");
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(intent);
                finish();
            }
        });
    }
}