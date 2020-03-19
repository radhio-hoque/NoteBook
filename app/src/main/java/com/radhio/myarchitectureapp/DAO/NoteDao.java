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
 The DAO is an interface that defines all the database operations we want to do on our entity.
 For this we declare methods without a method body and annotate them with @Insert, @Update, @Delete or the generic @Query,
 where we can pass an SQLite query.

 we can let these queries return instances of our own Java objects, which we can also wrap into LiveData,
 so our activity or fragment gets notified as soon as a row in the queried database table changes.

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

    @Query("SELECT * FROM note_table ORDER BY priority_table DESC")
    LiveData<List<Note>> getALlNotes();
}
