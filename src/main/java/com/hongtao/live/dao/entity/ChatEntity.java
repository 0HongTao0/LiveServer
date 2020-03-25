package com.hongtao.live.dao.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/25.
 *
 * @author HongTao
 */
@Entity
@Table(name = "chat", schema = "Live", catalog = "Live")
public class ChatEntity {
    private int mChatId;
    private String mUserId;
    private int mRoomId;
    private String mMessage;
    private Timestamp mTime;
    private int mType;

    @Id
    @Column(name = "chatId")
    public int getChatId() {
        return mChatId;
    }

    public void setChatId(int chatId) {
        mChatId = chatId;
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
    @Column(name = "roomId")
    public int getRoomId() {
        return mRoomId;
    }

    public void setRoomId(int roomId) {
        mRoomId = roomId;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return mTime;
    }

    public void setTime(Timestamp time) {
        mTime = time;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return mChatId == that.mChatId &&
                mRoomId == that.mRoomId &&
                mType == that.mType &&
                Objects.equals(mUserId, that.mUserId) &&
                Objects.equals(mMessage, that.mMessage) &&
                Objects.equals(mTime, that.mTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mChatId, mUserId, mRoomId, mMessage, mTime, mType);
    }
}
