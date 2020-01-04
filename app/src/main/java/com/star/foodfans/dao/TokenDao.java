package com.star.foodfans.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.star.foodfans.entity.Token;

@Dao
public interface TokenDao {

    @Query("DELETE FROM Token")
    void deleteAll();

    @Insert
    void insertToken(Token token);
}
