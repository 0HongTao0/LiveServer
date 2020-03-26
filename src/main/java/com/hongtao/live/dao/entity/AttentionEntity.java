package com.hongtao.live.dao.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/26.
 *
 * @author HongTao
 */
@Entity
@Table(name = "attention", schema = "Live", catalog = "Live")
public class AttentionEntity {
    private String mUserId;
    private int mRoomId;
    private int mAttentionId;
    private Timestamp mTime;

    @Basic
    @Column(name = "userId")
    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    @Basic
    @Column(name = "roomId")
    public int getRoomId() {
        return mRoomId;
    }

    public void setRoomId(int roomId) {
        mRoomId = roomId;
    }

    @Id
    @Column(name = "attentionId")
    public int getAttentionId() {
        return mAttentionId;
    }

    public void setAttentionId(int attentionId) {
        mAttentionId = attentionId;
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
        AttentionEntity that = (AttentionEntity) o;
        return mRoomId == that.mRoomId &&
                mAttentionId == that.mAttentionId &&
                Objects.equals(mUserId, that.mUserId) &&
                Objects.equals(mTime, that.mTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUserId, mRoomId, mAttentionId, mTime);
    }
}
