package com.ymt.components.database;

import androidx.room.Dao;
import androidx.room.Insert;

/**
 * @author michcode
 */
@Dao
public interface NotaDao {

    @Insert
    void insert(Nota nota);
}
