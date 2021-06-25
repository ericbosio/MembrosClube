package com.example.membrosclube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // CRIANDO EditText e Button

    EditText editText_name,editText_rg,editText_telefone,editText_email;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.edittext_name);
        editText_rg = findViewById(R.id.edittext_rg);
        editText_telefone = findViewById(R.id.edittext_telefone);
        editText_email = findViewById(R.id.edittext_email);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);

// Ao clicar no botão Incluir Membro chama o valor EditText na string

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName = editText_name.getText().toString();
                String stringRg = editText_rg.getText().toString();
                String stringTelefone = editText_telefone.getText().toString();
                String stringEmail = editText_email.getText().toString();

// Verifique a validação se o valor da string estiver vazio

                if (stringName.length() <=0 || stringRg.length() <=0 || stringTelefone.length() <=0 || stringEmail.length() <=0){
                    Toast.makeText(MainActivity.this, "Preencher Todos os Campos", Toast.LENGTH_SHORT).show();
                }


                else {
                    //criar o objeto DatabaseHelperClass e chame o método addMembros e passar os dados
                    SQLiteClass SQLiteClass = new SQLiteClass(MainActivity.this);
                    MembrosClass membrosClass = new MembrosClass(stringName,stringRg,stringTelefone,stringEmail);
                    SQLiteClass.addMembros(membrosClass);
                    Toast.makeText(MainActivity.this, "Cadastrado com Sucessos", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MembrosCadastrados.class);
                startActivity(intent);
            }
        });


    }
}