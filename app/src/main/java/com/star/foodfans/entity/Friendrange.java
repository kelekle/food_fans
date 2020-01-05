package com.star.foodfans.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(primaryKeys = {"userid", "friendid"})
public class Friendrange implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friendrange.userId
     *
     * @mbggenerated
     */
    @NonNull
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friendrange.friendId
     *
     * @mbggenerated
     */
    @NonNull
    private Integer friendid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friendrange.creatTimeStamp
     *
     * @mbggenerated
     */
    private Long creattimestamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friendrange.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table friendrange
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friendrange.userId
     *
     * @return the value of friendrange.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friendrange.userId
     *
     * @param userid the value for friendrange.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friendrange.friendId
     *
     * @return the value of friendrange.friendId
     *
     * @mbggenerated
     */
    public Integer getFriendid() {
        return friendid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friendrange.friendId
     *
     * @param friendid the value for friendrange.friendId
     *
     * @mbggenerated
     */
    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friendrange.creatTimeStamp
     *
     * @return the value of friendrange.creatTimeStamp
     *
     * @mbggenerated
     */
    public Long getCreattimestamp() {
        return creattimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friendrange.creatTimeStamp
     *
     * @param creattimestamp the value for friendrange.creatTimeStamp
     *
     * @mbggenerated
     */
    public void setCreattimestamp(Long creattimestamp) {
        this.creattimestamp = creattimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friendrange.status
     *
     * @return the value of friendrange.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friendrange.status
     *
     * @param status the value for friendrange.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friendrange
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", friendid=").append(friendid);
        sb.append(", creattimestamp=").append(creattimestamp);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}