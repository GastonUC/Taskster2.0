package cl.inacap.taskster20;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList nota_id, nota_title, nota_descrip;

    CustomAdapter(Context context, ArrayList nota_id, ArrayList nota_title, ArrayList nota_descrip) {
        this.context = context;
        this.nota_id = nota_id;
        this.nota_title = nota_title;
        this.nota_descrip = nota_descrip;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mi_lista, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nota_id_txt.setText(String.valueOf(nota_id.get(position)));
        holder.nota_title_txt.setText(String.valueOf(nota_title.get(position)));
        holder.nota_descrip_txt.setText(String.valueOf(nota_descrip.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModificarActivity.class);
                intent.putExtra("id", String.valueOf(nota_id.get(position)));
                intent.putExtra("title",String.valueOf(nota_title.get(position)));
                intent.putExtra("descrip",String.valueOf(nota_descrip.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nota_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nota_id_txt, nota_title_txt, nota_descrip_txt;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nota_id_txt = itemView.findViewById(R.id.id_nota_txt);
            nota_title_txt = itemView.findViewById(R.id.tit_nota);
            nota_descrip_txt = itemView.findViewById(R.id.descrip_nota);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
