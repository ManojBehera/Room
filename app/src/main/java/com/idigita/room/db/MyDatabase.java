package com.idigita.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.idigita.room.dao.ToDoDao;

@Database(entities = {ToDo.class} , version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ToDoDao getDao();

}
