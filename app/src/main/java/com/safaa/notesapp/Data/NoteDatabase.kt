package com.safaa.notesapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var DBInstance: NoteDatabase? = null


        fun getDatabase(context: Context): NoteDatabase {
            val tempInstance = DBInstance
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "noteDatabase"
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
                DBInstance = instance
                return instance
            }
        }
    }

}