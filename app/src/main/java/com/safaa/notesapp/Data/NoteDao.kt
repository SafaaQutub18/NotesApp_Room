package com.safaa.notesapp.Data

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveNote(note: Note)

    @Query("SELECT * FROM NotesTable ORDER BY pk ASC")
    fun readNotes(): List<Note>

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

}