package com.example.membrosclube;

// SQLite que irá gerenciar o trabalho do banco de dados

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Nome do Banco de Dados
    private static final String DATABASE_NAME = "membro_database";

    private static final String TABLE_NAME = "MEMBROS";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String RG = "rg";
    public static final String TELEFONE = "telefone";
    public static final String EMAIL = "email";
    private SQLiteDatabase sqLiteDatabase;


    //tabela de consulta

    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL,"+RG+" TEXT NOT NULL,"+TELEFONE+" TEXT NOT NULL);";

    //Constructor
    public SQLiteClass(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // subclasse que substitui os métodos de retorno de chamada Oncreate e onUpgrade

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Incluir Dados do Membro
    // insirir os dados do membro no banco de dados, passando um objeto ContValues para o método insert ()
    public void addMembros(MembrosClass membrosClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteClass.NAME, membrosClass.getName());
        contentValues.put(SQLiteClass.RG, membrosClass.getRg());
        contentValues.put(SQLiteClass.TELEFONE, membrosClass.getTelefone());
        contentValues.put(SQLiteClass.EMAIL, membrosClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(SQLiteClass.TABLE_NAME, null,contentValues);
    }

    // método getEmployeeList

    public List<MembrosClass> getMembrosList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<MembrosClass> storeMembros = new ArrayList<>();

    // para recuperar qualquer informações do banco de dados usando um objeto da class Cursor

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String rg = cursor.getString(2);
                String telefone = cursor.getString(3);
                String email = cursor.getString(4);
                storeMembros.add(new MembrosClass(id,name,rg,telefone,email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeMembros;
    }

    public void updateMembros(MembrosClass membrosClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteClass.NAME, membrosClass.getName());
        contentValues.put(SQLiteClass.RG, membrosClass.getRg());
        contentValues.put(SQLiteClass.TELEFONE, membrosClass.getTelefone());
        contentValues.put(SQLiteClass.EMAIL, membrosClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(membrosClass.getId())});
    }

    public void deleteMembros(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}