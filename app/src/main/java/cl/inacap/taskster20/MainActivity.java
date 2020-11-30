package cl.inacap.taskster20;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    NotasDatabaseHelper myDB;
    ArrayList<String> nota_id, nota_title, nota_descrip;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new NotasDatabaseHelper(MainActivity.this);
        nota_id = new ArrayList<>();
        nota_title = new ArrayList<>();
        nota_descrip = new ArrayList<>();

        guardarDataArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, nota_id, nota_title, nota_descrip);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    void guardarDataArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No hay data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                nota_id.add(cursor.getString(0));
                nota_title.add(cursor.getString(1));
                nota_descrip.add(cursor.getString(2));
            }
        }
    }
}