package com.star.foodfans.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

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


@Database(entities = {Achievemenkindtinfo.class, Articleinfo.class, Commentinfo.class,
        Friendrange.class, Goods.class, History.class, Message.class, Record.class,
        Reward.class, Userinfo.class, Videoinfo.class, Token.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AchievemenkindtinfoDao achievemenkindtinfoDao();
    public abstract ArticleinfoDao articleinfoDao();
    public abstract CommentinfoDao commentinfoDao();
    public abstract FriendrangeDao friendrangeDao();
    public abstract GoodsDao goodsDao();
    public abstract HistoryDao historyDao();
    public abstract MessageDao messageDao();
    public abstract RecordDao recordDao();
    public abstract RewardDao rewardDao();
    public abstract UserinfoDao userinfoDao();
    public abstract VideoinfoDao videoinfoDao();
    public abstract TokenDao tokenDao();



}
