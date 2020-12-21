package cl.inacap.taskster20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private Context context;
    Activity activity;
    EditText titleNota, descripNota;
    Button add_button;
    ImageButton date_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titleNota = findViewById(R.id.titleNota);
        descripNota = findViewById(R.id.descripNota);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titleNota.getText().toString().trim().equals("") || descripNota.getText().toString().trim().equals("")) {
                    titleNota.startAnimation(shakeError());
                    descripNota.startAnimation(shakeError());
                    Toast.makeText(AddActivity.this, "Ingrese algo en los campos de texto!!", Toast.LENGTH_SHORT).show();
                }else{
                    NotasDatabaseHelper myDB = new NotasDatabaseHelper(AddActivity.this);
                    myDB.addNote(titleNota.getText().toString().trim(), descripNota.getText().toString().trim());

                    Intent intent = new Intent(context, ModificarActivity.class);
                    activity.startActivityForResult(intent, 1); //Es para poder actualizar el activity de las notas para que sea visible la modificación
                }
            }
        });
        date_button = findViewById(R.id.date_button);
        date_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(AddActivity.this, "Se ha presionado botón Calendario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Método para animar la validación de los EditText
    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
}