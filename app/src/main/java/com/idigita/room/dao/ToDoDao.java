package com.idigita.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.idigita.room.db.ToDo;
import java.util.List;

@Dao
public interface ToDoDao {
    @Query("Select * from todo")
    List<ToDo> getLists();
    @Insert
    void insertData(ToDo toDo);
    @Update
    void updateData(ToDo toDo);
    @Delete
    void deleteData(ToDo toDo);
}
