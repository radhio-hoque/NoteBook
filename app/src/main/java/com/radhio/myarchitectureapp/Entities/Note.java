package com.radhio.myarchitectureapp.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/*
 In Compile time @Entity annotation will create will the necessary code to create SQLite Table for this object,
 as well as columns for all it's fields.
 With @PrimaryKey and autoGenerate = true, we can turn an integer member variable into an autoincrementing primary key,
 which we can use to uniquely identify each row in the table.
 We can use Room @annotation as we use SQLiteOpenHelper Class
 */

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    @ColumnInfo(name = "priority_table")
    private int priority;
    private String className;

    public Note(String title, String description, int priority, String className) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.className = className;
    }

    // not to recreate this field in room thats why only setter method
    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
