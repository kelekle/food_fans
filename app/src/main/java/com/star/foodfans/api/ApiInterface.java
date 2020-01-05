package com.star.foodfans.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.star.foodfans.entity.Result;
import com.star.foodfans.entity.SearchResultBean;
import com.star.foodfans.entity.Userinfo;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user/register")
    Call<JsonObject> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("code") String code
    );

    @FormUrlEncoded
    @POST("user/find_pwd")
    Call<JsonObject> findPassword(
            @Field("email") String email,
            @Field("password") String password,
            @Field("code") String code
    );

    @FormUrlEncoded
    @POST("user/get_code")
    Call<JsonObject> getCode(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("user/change_username")
    Call<JsonObject> changeUsername(
            @Field("email") String email,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("user/change_description")
    Call<JsonObject> changeDescription(
            @Field("email") String email,
            @Field("description") String description
    );

    @Multipart
    @POST("user/change_photo")
    Call<JsonObject> changePhoto(
            @Part("email") RequestBody email, @Part MultipartBody.Part photo
    );

    @Streaming
    @GET("user/get_user_photo")
    Call<ResponseBody> getUserPhoto(
            @Query("name") String name
    );

    @GET("user/get_user")
    Call<Userinfo> getUser(
            @Query("email") String email
    );



    @GET("/allArticle")
    Call<JsonObject> getAllArticle();

    @FormUrlEncoded
    @POST("/search_articles")
    Call<JsonArray> searchArticle(
            @Field("dish") String dish,
            @Field("cuisine") String cuisine
    );

    @FormUrlEncoded
    @POST("/add_collection_article")
    Call<JsonObject> collectArticle(
            @Field("contentId") String contentId
    );

    @FormUrlEncoded
    @POST("/isCollectArticle")
    Call<JsonObject> isCollectArticle(
            @Field("articleid") int articleid
    );

    @FormUrlEncoded
    @POST("/cancelCollectArticle")
    Call<JsonObject> cancelCollectArticle(
            @Field("articleid") int articleid
    );

    @FormUrlEncoded
    @POST("/queryArticle")
    Call<JsonObject> queryArticle(
            @Field("articleid") int articleid
    );

    @GET("/get_personal_collection")
    Call<JsonArray> getCollection(
    );

    @GET("/get_personal_history")
    Call<JsonArray> getHistory(
    );

    @FormUrlEncoded
    @POST("/commentArticle")
    Call<JsonObject> comment(
            @Field("score") int score,
            @Field("url") String url,
            @Field("content") String content,
            @Field("articleid") int articleid
    );

}
