package com.star.foodfans.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.star.foodfans.entity.Userinfo;

import java.util.List;

@Dao
public interface UserinfoDao {

    @Query("DELETE FROM Userinfo WHERE userid = :userid")
    int deleteUserinfo(Integer userid);

    @Query("DELETE FROM Userinfo")
    int deleteAllUserinfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserinfo(Userinfo record);

    @Query("SELECT * FROM Userinfo WHERE userid = :userid")
    LiveData<Userinfo> selectUserinfo(Integer userid);

    @Query("SELECT * FROM Userinfo")
    LiveData<List<Userinfo>> selectAllUserinfo();

    @Query("SELECT * FROM Userinfo WHERE email = :email")
    LiveData<Userinfo> selectUserinfoByEmail(String email);

    @Update
    int updateUserinfo(Userinfo record);

//    int insertByEmail(String username, String email, String password);
//
//    int updateUserInfo(Integer userid, String username, String headpicture, String phone);
//
//    Userinfo selectByEmail(String email);
//
//    String selectPasswordByUserid(Integer userid);
//
//    int changePassword(Integer userid, String newPassword);
//
//    int changePasswordByEmail(String email, String newPassword);
//
//    List<Userinfo> selectNameByArticle(Integer articleid);
//
//    List<Userinfo> selectNameByVideo(Integer articleid);

}