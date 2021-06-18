package com.example.membrosclube;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    List<MembrosClass> membro;
    Context context;
    SQLiteClass SQLiteClass;

    public AdapterClass(List<MembrosClass> membro, Context context) {
        this.membro = membro;
        this.context = context;
        SQLiteClass = new SQLiteClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_membros,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MembrosClass membrosClass = membro.get(position);

        holder.textViewID.setText(Integer.toString(membrosClass.getId()));
        holder.editText_Name.setText(membrosClass.getName());
        holder.editText_Rg.setText(membrosClass.getRg());
        holder.editText_Telefone.setText(membrosClass.getTelefone());
        holder.editText_Email.setText(membrosClass.getEmail());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringRg = holder.editText_Rg.getText().toString();
                String stringTelefone = holder.editText_Telefone.getText().toString();
                String stringEmail = holder.editText_Email.getText().toString();

                SQLiteClass.updateMembros(new MembrosClass(membrosClass.getId(),stringName,stringEmail,stringRg,stringTelefone));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteClass.deleteMembros(membrosClass.getId());
                membro.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return membro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Name;
        EditText editText_Rg;
        EditText editText_Telefone;
        EditText editText_Email;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Rg = itemView.findViewById(R.id.edittext_rg);
            editText_Telefone = itemView.findViewById(R.id.edittext_telefone);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}