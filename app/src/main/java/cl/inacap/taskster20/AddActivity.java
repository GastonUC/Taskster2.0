package cl.inacap.taskster20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText titleNota, descripNota;
    Button add_button;

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
                NotasDatabaseHelper myDB = new NotasDatabaseHelper(AddActivity.this);
                myDB.addNote(titleNota.getText().toString().trim(),
                        descripNota.getText().toString().trim());
            }
        });
    }
}