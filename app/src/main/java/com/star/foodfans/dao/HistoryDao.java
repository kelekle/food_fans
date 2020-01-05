package com.star.foodfans.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.star.foodfans.entity.History;
import java.util.List;

@Dao
public interface HistoryDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated
     */
    @Query("DELETE FROM History WHERE userid = :userid and contentid = :contentid")
    int deleteHistory(Integer userid, String contentid);

    @Query("DELETE FROM History")
    int deleteAllHistory();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated
     */
    @Insert
    void insertHistory(History record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated
     */
    @Query("SELECT * FROM History WHERE userid = :userid and contentid = :contentid")
    LiveData<History> selectHistory(Integer userid, String contentid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated
     */
    @Query("SELECT * FROM History")
    LiveData<List<History>> selectAllHistory();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated
     */
    @Update
    int updateHistory(History record);

}