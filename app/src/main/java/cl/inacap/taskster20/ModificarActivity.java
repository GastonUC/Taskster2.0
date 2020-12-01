package cl.inacap.taskster20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarActivity extends AppCompatActivity {

    EditText title_nota, descrip_nota;
    Button update_button, delete_button;

    String id, title, descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        title_nota = findViewById(R.id.titleModNota);
        descrip_nota = findViewById(R.id.descripModNota);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //Primero se llama este
        getAndSetIntentData();

        update_button.setOnClickListener((view) -> {
                //Y luego de eso se puede llamar a este método
                NotasDatabaseHelper myDB = new NotasDatabaseHelper(ModificarActivity.this);
                title = title_nota.getText().toString().trim();
                descrip = descrip_nota.getText().toString().trim();
                myDB.actualizarData(id, title, descrip);
                finish();
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoConfirmacion();
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("descrip")) {
            //Obtención de Data de los Intents
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            descrip = getIntent().getStringExtra("descrip");

            //Setteando Data del Intent
            title_nota.setText(title);
            descrip_nota.setText(descrip);
        }else{
            Toast.makeText(this, "No hay data.", Toast.LENGTH_SHORT).show();
        }
    }

    void dialogoConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Deseas eliminar " + title + "?");
        builder.setMessage("¿Esta seguro que quiere eliminar " + title + "?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NotasDatabaseHelper myDB = new NotasDatabaseHelper(ModificarActivity.this);
                myDB.borrarFila(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //No lleva nada este método
            }
        });
        builder.create().show();
    }
}