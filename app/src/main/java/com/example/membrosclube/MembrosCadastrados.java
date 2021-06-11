package com.example.membrosclube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MembrosCadastrados extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrados);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SQLiteClass SQLiteClass = new SQLiteClass(this);
        List<MembrosClass> membrosClasses = SQLiteClass.getEmployeeList();

        if (membrosClasses.size() > 0){
            AdapterClass employeadapterclass = new AdapterClass(membrosClasses, MembrosCadastrados.this);
            recyclerView.setAdapter(employeadapterclass);
        }else {
            Toast.makeText(this, "Não tem membros cadastrados", Toast.LENGTH_SHORT).show();
        }




    }
}