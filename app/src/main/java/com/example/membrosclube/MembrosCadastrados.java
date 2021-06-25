package com.example.membrosclube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

// class para visualizar todos os dados do Membro

public class MembrosCadastrados extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrados);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    // Objeto DatabaseHelperClass e chame o método getMembroList e defina em RecyclerView AdapterCalss

        SQLiteClass SQLiteClass = new SQLiteClass(this);
        List<MembrosClass> membrosClasses = SQLiteClass.getMembrosList();

        if (membrosClasses.size() > 0){
            AdapterClass membroadapterclass = new AdapterClass(membrosClasses, MembrosCadastrados.this);
            recyclerView.setAdapter(membroadapterclass);
        }

        else {
                  Toast.makeText(this, "Não tem membros cadastrados", Toast.LENGTH_SHORT).show();
        }

    }
}