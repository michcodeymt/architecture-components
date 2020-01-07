package com.ymt.components.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author michcode
 */
@Dao
public interface NotaDao {

    @Insert
    void insert(Nota nota);

    @Update
    void update(Nota nota);

    @Query("select * from tabla_notas order by id desc")
    LiveData<List<Nota>> listarNotas();

    @Delete
    void delete(Nota nota);
}
