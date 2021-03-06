package com.star.foodfans.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.star.foodfans.entity.Videoinfo;

import java.util.List;

@Dao
public interface VideoinfoDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videoinfo
     *
     * @mbggenerated
     */
    @Query("DELETE FROM Videoinfo WHERE videoid = :videoid")
    int deleteVideoinfo(Integer videoid);

    @Query("DELETE FROM Videoinfo")
    int deleteAllVideoinfo();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videoinfo
     *
     * @mbggenerated
     */
    @Insert
    void insertVideoinfo(Videoinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videoinfo
     *
     * @mbggenerated
     */
    @Query("SELECT * FROM Videoinfo WHERE videoid = :videoid")
    LiveData<Videoinfo> selectVideoinfo(Integer videoid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videoinfo
     *
     * @mbggenerated
     */
    @Query("SELECT * FROM Videoinfo")
    List<Videoinfo> selectAllVideoinfo();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videoinfo
     *
     * @mbggenerated
     */
    @Update
    int updateVideoinfo(Videoinfo record);

    @Query("SELECT * FROM Videoinfo WHERE userid = :userid ")
    LiveData<List<Videoinfo>> selectVideoCollectionByUserid(int userid);

//    LiveData<List<Videoinfo>> selectVideoPublishByUserid(int userid);
//
//    LiveData<List<Videoinfo>> selectVideoHistoryByUserid(int userid);
//
//    List<Videoinfo> selectByHot();

}