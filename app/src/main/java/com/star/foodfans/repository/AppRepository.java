package com.star.foodfans.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.star.foodfans.dao.AchievemenkindtinfoDao;
import com.star.foodfans.dao.ArticleinfoDao;
import com.star.foodfans.dao.CommentinfoDao;
import com.star.foodfans.dao.FriendrangeDao;
import com.star.foodfans.dao.GoodsDao;
import com.star.foodfans.dao.HistoryDao;
import com.star.foodfans.dao.MessageDao;
import com.star.foodfans.dao.RecordDao;
import com.star.foodfans.dao.RewardDao;
import com.star.foodfans.dao.TokenDao;
import com.star.foodfans.dao.UserinfoDao;
import com.star.foodfans.dao.VideoinfoDao;
import com.star.foodfans.db.AppDatabase;
import com.star.foodfans.entity.Achievemenkindtinfo;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Commentinfo;
import com.star.foodfans.entity.Friendrange;
import com.star.foodfans.entity.Goods;
import com.star.foodfans.entity.History;
import com.star.foodfans.entity.Message;
import com.star.foodfans.entity.Record;
import com.star.foodfans.entity.Reward;
import com.star.foodfans.entity.Token;
import com.star.foodfans.entity.Userinfo;
import com.star.foodfans.entity.Videoinfo;

import java.util.List;
import java.util.Map;

import static com.star.foodfans.util.AppConfig.DB_NAME;

public class AppRepository implements AchievemenkindtinfoDao, ArticleinfoDao,
        CommentinfoDao, FriendrangeDao, GoodsDao, HistoryDao, MessageDao, RecordDao,
        RewardDao, UserinfoDao, VideoinfoDao, TokenDao {

    private static AppRepository INSTANCE;

    private AppDatabase appDatabase;

    private AppRepository(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public static AppRepository getAppRepository(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRepository.class) {
                if (INSTANCE == null)    {
                    INSTANCE = new AppRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public int deleteAchievementkindinfo(final Integer typeid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.achievemenkindtinfoDao().deleteAchievementkindinfo(typeid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllAchievemenkindtinfo() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.achievemenkindtinfoDao().deleteAllAchievemenkindtinfo();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertAchievemenkindtinfo(final Achievemenkindtinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.achievemenkindtinfoDao().insertAchievemenkindtinfo(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Achievemenkindtinfo> selectAchievemenkindtinfo(Integer typeid) {
        return appDatabase.achievemenkindtinfoDao().selectAchievemenkindtinfo(typeid);
    }

    @Override
    public LiveData<List<Achievemenkindtinfo>> selectAllAchievemenkindtinfo() {
        return appDatabase.achievemenkindtinfoDao().selectAllAchievemenkindtinfo();
    }

    @Override
    public int updateAchievemenkindtinfo(final Achievemenkindtinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.achievemenkindtinfoDao().updateAchievemenkindtinfo(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteArticleinfo(Integer articleid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.articleinfoDao().deleteAllArticleinfo();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllArticleinfo() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.articleinfoDao().deleteAllArticleinfo();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertArticleinfo(final Articleinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.articleinfoDao().insertArticleinfo(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Articleinfo> selectArticleinfo(Integer articleid) {
        return appDatabase.articleinfoDao().selectArticleinfo(articleid);
    }

    @Override
    public LiveData<List<Articleinfo>> selectAllArticleinfo() {
        return appDatabase.articleinfoDao().selectAllArticleinfo();
    }
//
//    @Override
//    public int updateArticleinfo(final Articleinfo record) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                appDatabase.articleinfoDao().updateArticleinfo(record);
//                return null;
//            }
//        }.execute();
//        return 0;
//    }
//
//    @Override
//    public LiveData<List<Articleinfo>> selectArticleCollectionByUserid(int userid) {
//        return appDatabase.articleinfoDao().selectArticleCollectionByUserid(userid);
//    }
//
//    @Override
//    public LiveData<List<Articleinfo>> selectArticlePublishByUserid(int userid) {
//        return appDatabase.articleinfoDao().selectArticlePublishByUserid(userid);
//    }
//
//    @Override
//    public LiveData<List<Articleinfo>> selectArticleHistoryByUserid(int userid) {
//        return appDatabase.articleinfoDao().selectArticleHistoryByUserid(userid);
//    }
//
//    @Override
//    public LiveData<List<Articleinfo>> getHotArticles() {
//        return appDatabase.articleinfoDao().getHotArticles();
//    }
//
//    @Override
//    public LiveData<List<Map>> searchArticles(String dish, String cuisine) {
//        return appDatabase.articleinfoDao().searchArticles(dish, cuisine);
//    }

    @Override
    public int deleteCommentinfo(final Integer commentid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.commentinfoDao().deleteCommentinfo(commentid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllCommentinfo() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.commentinfoDao().deleteAllCommentinfo();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertCommentinfo(final Commentinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.commentinfoDao().insertCommentinfo(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Commentinfo> selectCommentinfo(Integer commentid) {
        return appDatabase.commentinfoDao().selectCommentinfo(commentid);
    }

    @Override
    public LiveData<List<Commentinfo>> selectAllCommentinfo() {
        return appDatabase.commentinfoDao().selectAllCommentinfo();
    }

    @Override
    public int updateCommentinfo(final Commentinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.commentinfoDao().updateCommentinfo(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public LiveData<List<Commentinfo>> selectCommentinfoByContentid(String contentid) {
        return appDatabase.commentinfoDao().selectCommentinfoByContentid(contentid);
    }

    @Override
    public int deleteAllFriendrange() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.friendrangeDao().deleteAllFriendrange();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteFriendrange(final Integer userid, final Integer friendid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.friendrangeDao().deleteFriendrange(userid, friendid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insert(final Friendrange record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.friendrangeDao().insert(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Friendrange> selectFriendrange(Integer userid, Integer friendid) {
        return appDatabase.friendrangeDao().selectFriendrange(userid, friendid);
    }

    @Override
    public LiveData<List<Friendrange>> selectAllFriendrange() {
        return appDatabase.friendrangeDao().selectAllFriendrange();
    }

    @Override
    public int updateFriendrange(final Friendrange record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.friendrangeDao().updateFriendrange(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public LiveData<List<Friendrange>> selectPersonalFriends(Integer userid) {
        return appDatabase.friendrangeDao().selectPersonalFriends(userid);
    }

    @Override
    public int deleteGoods(final Integer id) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.goodsDao().deleteGoods(id);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllGoods() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.goodsDao().deleteAllGoods();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertGoods(final Goods record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.goodsDao().insertGoods(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Goods> selectGoods(Integer id) {
        return appDatabase.goodsDao().selectGoods(id);
    }

    @Override
    public LiveData<List<Goods>> selectAllGoods() {
        return appDatabase.goodsDao().selectAllGoods();
    }

    @Override
    public int updateGoods(final Goods record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.goodsDao().updateGoods(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteHistory(final Integer userid, final String contentid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.historyDao().deleteHistory(userid, contentid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllHistory() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.historyDao().deleteAllHistory();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertHistory(final History record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.historyDao().insertHistory(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<History> selectHistory(Integer userid, String contentid) {
        return appDatabase.historyDao().selectHistory(userid, contentid);
    }

    @Override
    public LiveData<List<History>> selectAllHistory() {
        return appDatabase.historyDao().selectAllHistory();
    }

    @Override
    public int updateHistory(final History record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.historyDao().updateHistory(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteMessage(final Integer msgid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.messageDao().deleteMessage(msgid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllMessage() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.messageDao().deleteAllMessage();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertMessage(final Message record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.messageDao().insertMessage(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Message> selectMessage(Integer msgid) {
        return appDatabase.messageDao().selectMessage(msgid);
    }

    @Override
    public LiveData<List<Message>> selectAllMessage() {
        return appDatabase.messageDao().selectAllMessage();
    }

    @Override
    public int updateMessage(final Message record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.messageDao().updateMessage(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public LiveData<List<Message>> selectOnesMessage(Integer uid) {
        return appDatabase.messageDao().selectOnesMessage(uid);
    }

    @Override
    public void readMessage(final Integer uid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.messageDao().readMessage(uid);
                return null;
            }
        }.execute();
    }

    @Override
    public int deleteRecord(final Integer id) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.recordDao().deleteRecord(id);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllRecord() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.recordDao().deleteAllRecord();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertRecord(final Record record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.recordDao().insertRecord(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Record> selectRecord(Integer id) {
        return appDatabase.recordDao().selectRecord(id);
    }

    @Override
    public LiveData<List<Record>> selectAllRecord() {
        return appDatabase.recordDao().selectAllRecord();
    }

    @Override
    public int updateRecord(final Record record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.recordDao().updateRecord(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public LiveData<List<Record>> selectRecordByUserId(int id) {
        return appDatabase.recordDao().selectRecordByUserId(id);
    }

    @Override
    public int deleteReward(final Integer rewardid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.rewardDao().deleteReward(rewardid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllReward() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.rewardDao().deleteAllReward();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertReward(final Reward record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.rewardDao().insertReward(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Reward> selectReward(Integer rewardid) {
        return appDatabase.rewardDao().selectReward(rewardid);
    }

    @Override
    public LiveData<List<Reward>> selectAllReward() {
        return appDatabase.rewardDao().selectAllReward();
    }

    @Override
    public int updateReward(final Reward record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.rewardDao().updateReward(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteUserinfo(final Integer userid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.userinfoDao().deleteUserinfo(userid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllUserinfo() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.userinfoDao().deleteAllUserinfo();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertUserinfo(final Userinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.userinfoDao().insertUserinfo(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Userinfo> selectUserinfo(Integer userid) {
        return appDatabase.userinfoDao().selectUserinfo(userid);
    }

    @Override
    public LiveData<List<Userinfo>> selectAllUserinfo() {
        return appDatabase.userinfoDao().selectAllUserinfo();
    }

    @Override
    public LiveData<Userinfo> selectUserinfoByEmail(String email) {
        return appDatabase.userinfoDao().selectUserinfoByEmail(email);
    }

    @Override
    public int updateUserinfo(final Userinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.userinfoDao().updateUserinfo(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteVideoinfo(final Integer videoid) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.videoinfoDao().deleteVideoinfo(videoid);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public int deleteAllVideoinfo() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.videoinfoDao().deleteAllVideoinfo();
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public void insertVideoinfo(final Videoinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.videoinfoDao().insertVideoinfo(record);
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<Videoinfo> selectVideoinfo(Integer videoid) {
        return appDatabase.videoinfoDao().selectVideoinfo(videoid);
    }

    @Override
    public List<Videoinfo> selectAllVideoinfo() {
        return appDatabase.videoinfoDao().selectAllVideoinfo();
    }

    @Override
    public int updateVideoinfo(final Videoinfo record) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.videoinfoDao().updateVideoinfo(record);
                return null;
            }
        }.execute();
        return 0;
    }

    @Override
    public LiveData<List<Videoinfo>> selectVideoCollectionByUserid(int userid) {
        return appDatabase.videoinfoDao().selectVideoCollectionByUserid(userid);
    }

//    @Override
//    public LiveData<List<Videoinfo>> selectVideoPublishByUserid(int userid) {
//        return appDatabase.videoinfoDao().selectVideoPublishByUserid(userid);
//    }
//
//    @Override
//    public LiveData<List<Videoinfo>> selectVideoHistoryByUserid(int userid) {
//        return appDatabase.videoinfoDao().selectVideoHistoryByUserid(userid);
//    }

    @Override
    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.tokenDao().deleteAll();
                return null;
            }
        }.execute();
    }

    @Override
    public void insertToken(final Token token) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.tokenDao().insertToken(token);
                return null;
            }
        }.execute();
    }
}
