package com.radhio.myarchitectureapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.radhio.myarchitectureapp.Entities.Note;

import java.util.List;
/*
In Dao we define all DB operation to make on Note Entity.
Dao has to be Interface or Abstract class because we don't provide method body.
After annotating room will automatically generate all the code fast
 */
@Dao //annotated
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note Note);

    @Query("DELETE FROM note_table")
    void deleteAllNote();

    @Query("SELECT * FROM note_table ORDER BY property_table DESC")
    LiveData<List<Note>> getALlNotes();
}
