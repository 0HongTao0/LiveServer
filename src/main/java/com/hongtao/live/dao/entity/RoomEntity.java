package com.hongtao.live.dao.entity;

import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/18.
 *
 * @author HongTao
 */
@Entity
@Table(name = "room", schema = "Live", catalog = "Live")
public class RoomEntity {
    private int mRoomId;
    private int mNum;
    private String mUserId;
    private String mUrl;
    private int mLiving;
    private String mRoomName;
    private String mRoomIntroduction;

    @Id
    @Column(name = "roomId")
    public int getRoomId() {
        return mRoomId;
    }

    public void setRoomId(int roomId) {
        mRoomId = roomId;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return mNum;
    }

    public void setNum(int num) {
        mNum = num;
    }

    @Basic
    @Column(name = "userId")
    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Basic
    @Column(name = "living")
    public int getLiving() {
        return mLiving;
    }

    public void setLiving(int living) {
        mLiving = living;
    }

    @Basic
    @Column(name = "roomName")
    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String roomName) {
        mRoomName = roomName;
    }

    @Basic
    @Column(name = "roomIntroduction")
    public String getRoomIntroduction() {
        return mRoomIntroduction;
    }

    public void setRoomIntroduction(String roomIntroduction) {
        mRoomIntroduction = roomIntroduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return mRoomId == that.mRoomId &&
                mNum == that.mNum &&
                mLiving == that.mLiving &&
                Objects.equals(mUserId, that.mUserId) &&
                Objects.equals(mUrl, that.mUrl) &&
                Objects.equals(mRoomName, that.mRoomName) &&
                Objects.equals(mRoomIntroduction, that.mRoomIntroduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mRoomId, mNum, mUserId, mUrl, mLiving, mRoomName, mRoomIntroduction);
    }
}
