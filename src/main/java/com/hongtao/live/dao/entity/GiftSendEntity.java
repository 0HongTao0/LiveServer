package com.hongtao.live.dao.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/27.
 *
 * @author HongTao
 */
@Entity
@Table(name = "gift_send", schema = "Live", catalog = "Live")
public class GiftSendEntity {
    private int mId;
    private int mGiftId;
    private String mFromUserId;
    private String mToUserId;
    private Integer mRoomId;
    private Timestamp mTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Basic
    @Column(name = "giftId")
    public int getGiftId() {
        return mGiftId;
    }

    public void setGiftId(int giftId) {
        mGiftId = giftId;
    }

    @Basic
    @Column(name = "fromUserId")
    public String getFromUserId() {
        return mFromUserId;
    }

    public void setFromUserId(String fromUserId) {
        mFromUserId = fromUserId;
    }

    @Basic
    @Column(name = "toUserId")
    public String getToUserId() {
        return mToUserId;
    }

    public void setToUserId(String toUserId) {
        mToUserId = toUserId;
    }

    @Basic
    @Column(name = "roomId")
    public Integer getRoomId() {
        return mRoomId;
    }

    public void setRoomId(Integer roomId) {
        mRoomId = roomId;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return mTime;
    }

    public void setTime(Timestamp time) {
        mTime = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftSendEntity that = (GiftSendEntity) o;
        return mId == that.mId &&
                mGiftId == that.mGiftId &&
                Objects.equals(mFromUserId, that.mFromUserId) &&
                Objects.equals(mToUserId, that.mToUserId) &&
                Objects.equals(mRoomId, that.mRoomId) &&
                Objects.equals(mTime, that.mTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mGiftId, mFromUserId, mToUserId, mRoomId, mTime);
    }
}
