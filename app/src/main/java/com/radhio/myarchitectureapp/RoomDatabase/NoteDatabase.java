package com.radhio.myarchitectureapp.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.radhio.myarchitectureapp.DAO.NoteDao;
import com.radhio.myarchitectureapp.Entities.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //not to create multiple instance
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //use instance out side of the class
    //avoid multithreading use synchronized
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
