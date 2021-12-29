package com.safaa.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,"Notes.dp",null,2) {
    private val sqLiteDatabase:SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if(db!=null)
            db.execSQL("create table notes(pk INTEGER PRIMARY KEY AUTOINCREMENT,note text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun saveData(note:String){
        val contentValues = ContentValues()
        contentValues.put("Note" , note)
        sqLiteDatabase.insert("notes",null,contentValues)
    }

    fun readData(): ArrayList<Note>{
        var noteList = arrayListOf<Note>()

        //read all data using cursor var
        val cursor : Cursor = sqLiteDatabase.rawQuery("SELECT * FROM notes",null)

        if(cursor.count < 1)
            print("No data found")
        else{
            while(cursor.moveToNext()){
                //integer refers to colum
                noteList.add(Note(cursor.getInt(0),cursor.getString(1)))
            }
        }
       return noteList
    }

    fun updateData(note: Note){
        val contentValues = ContentValues()
        contentValues.put("Note" , note.text)
        sqLiteDatabase.update("notes",contentValues,"pk = ${note.pk}",null)
    }

    fun deleteData(note: Note){
        sqLiteDatabase.delete("notes","pk = ${note.pk}",null)
    }
}