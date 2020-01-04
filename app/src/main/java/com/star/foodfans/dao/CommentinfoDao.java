package com.star.foodfans.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.star.foodfans.entity.Commentinfo;

import java.util.List;

@Dao
public interface CommentinfoDao {

    @Query("DELETE FROM Commentinfo WHERE contentid = :commentid")
    int deleteCommentinfo(Integer commentid);

    @Query("DELETE FROM Commentinfo")
    int deleteAllCommentinfo();

    @Insert
    void insertCommentinfo(Commentinfo record);

    @Query("SELECT * FROM Commentinfo WHERE commentid = :commentid")
    LiveData<Commentinfo> selectCommentinfo(Integer commentid);

    @Query("SELECT * FROM Commentinfo")
    LiveData<List<Commentinfo>> selectAllCommentinfo();

    @Update
    int updateCommentinfo(Commentinfo record);

    @Query("SELECT * FROM Commentinfo WHERE contentid = :contentid")
    LiveData<List<Commentinfo>> selectCommentinfoByContentid(String contentid);

}