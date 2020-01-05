package com.star.foodfans.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Record implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.id
     *
     * @mbggenerated
     */
    @PrimaryKey
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.userid
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.good_id
     *
     * @mbggenerated
     */
    private Integer goodId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.is_finish
     *
     * @mbggenerated
     */
    private Integer isFinish;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.timestamp
     *
     * @mbggenerated
     */
    private Long timestamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record.scoreSpent
     *
     * @mbggenerated
     */
    private Integer scorespent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table record
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.id
     *
     * @return the value of record.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.id
     *
     * @param id the value for record.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.userid
     *
     * @return the value of record.userid
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.userid
     *
     * @param userid the value for record.userid
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.good_id
     *
     * @return the value of record.good_id
     *
     * @mbggenerated
     */
    public Integer getGoodId() {
        return goodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.good_id
     *
     * @param goodId the value for record.good_id
     *
     * @mbggenerated
     */
    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.is_finish
     *
     * @return the value of record.is_finish
     *
     * @mbggenerated
     */
    public Integer getIsFinish() {
        return isFinish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.is_finish
     *
     * @param isFinish the value for record.is_finish
     *
     * @mbggenerated
     */
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.timestamp
     *
     * @return the value of record.timestamp
     *
     * @mbggenerated
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.timestamp
     *
     * @param timestamp the value for record.timestamp
     *
     * @mbggenerated
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record.scoreSpent
     *
     * @return the value of record.scoreSpent
     *
     * @mbggenerated
     */
    public Integer getScorespent() {
        return scorespent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record.scoreSpent
     *
     * @param scorespent the value for record.scoreSpent
     *
     * @mbggenerated
     */
    public void setScorespent(Integer scorespent) {
        this.scorespent = scorespent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", goodId=").append(goodId);
        sb.append(", isFinish=").append(isFinish);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", scorespent=").append(scorespent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}