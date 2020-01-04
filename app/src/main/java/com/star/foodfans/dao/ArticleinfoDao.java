package com.star.foodfans.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.star.foodfans.entity.Articleinfo;

import java.util.List;
import java.util.Map;

@Dao
public interface ArticleinfoDao {

    @Query("DELETE FROM Articleinfo WHERE articleid = :articleid")
    int deleteArticleinfo(Integer articleid);

    @Query("DELETE FROM Articleinfo")
    int deleteAllArticleinfo();

    @Insert
    void insertArticleinfo(Articleinfo record);

    @Query("SELECT * FROM Articleinfo WHERE articleid = :articleid")
    LiveData<Articleinfo> selectArticleinfo(Integer articleid);

    @Query("SELECT * FROM Articleinfo")
    LiveData<List<Articleinfo>> selectAllArticleinfo();

//    @Update
//    int updateArticleinfo(Articleinfo record);
//
//    @Query("select articleId, articleinfo.userId, title, urls, praiseNum, readNum, commentNum, avgscore," +
//            "    creatStamp, isTop, visitNum, tag, content" +
//            "    from history, articleinfo" +
//            "    where history.userId = :userid and history.contentId = concat('A', articleinfo.articleId) and history.isCollected = 1")
//    LiveData<List<Articleinfo>> selectArticleCollectionByUserid(int userid);
//
//    @Query("select articleId, userId, title, urls, praiseNum, readNum, commentNum, avgscore,\n" +
//            "    creatStamp, isTop, visitNum, tag, content" +
//            "    from articleinfo\n" +
//            "    where articleinfo.userId = :userid")
//    LiveData<List<Articleinfo>> selectArticlePublishByUserid(int userid);
//
//
//    @Query("select articleId, articleinfo.userId, title, urls, praiseNum, readNum, commentNum, avgscore," +
//            "    creatStamp, isTop, visitNum, tag, content" +
//            "    from articleinfo, history" +
//            "    where history.userId = :userid and history.userId = articleinfo.userid and history.contentId = articleinfo.articleId")
//    LiveData<List<Articleinfo>> selectArticleHistoryByUserid(int userid);
//
//    @Query("select articleId, userId, title, urls, praiseNum, readNum, commentNum, avgscore," +
//            "    creatStamp, isTop, visitNum, tag, content" +
//            "    from articleinfo\n" +
//            "    limit 4\n")
//    LiveData<List<Articleinfo>> getHotArticles();
//
//    @Query("select articleId, articleinfo.userId, title, urls, praiseNum, readNum, commentNum, " +
//            "avgscore, creatStamp, isTop, visitNum, tag, content, username, " +
//            "((visitNum + 0.2 * commentNum) * avgscore) as hot " +
//            "FROM articleinfo, userinfo " +
//            "where articleinfo.userId = userinfo.userId " +
//            "and (articleinfo.tag LIKE :cuisine or articleinfo.title LIKE :dish) " +
//            "limit 10")
//    LiveData<List<Map>> searchArticles(String dish, String cuisine);

//    List<Articleinfo> selectTen();
}