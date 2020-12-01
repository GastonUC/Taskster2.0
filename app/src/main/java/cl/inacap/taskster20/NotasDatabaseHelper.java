package cl.inacap.taskster20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NotasDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Todo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "nota_titulo";
    private static final String COLUMN_DESCRIP = "nota_descrip";


    NotasDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TINYTEXT, " +
                        COLUMN_DESCRIP + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNote(String title, String descrip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIP, descrip);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Falló", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Añadido satisfactoriamente!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void actualizarData(String row_id, String title, String descrip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIP, descrip);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Fallo al Actualizar.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Actualizado Satisfactoriamente!", Toast.LENGTH_SHORT).show();
        }
    }

    void borrarFila(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Ha ocurrido un fallo al eliminar.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Se eliminó correctamente.", Toast.LENGTH_SHORT).show();
        }
    }
}
