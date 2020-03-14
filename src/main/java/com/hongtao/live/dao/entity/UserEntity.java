package com.hongtao.live.dao.entity;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/14.
 *
 * @author HongTao
 */
@Entity
@Table(name = "user", schema = "Live", catalog = "Live")
public
class UserEntity {
    private int mId;
    private String mUserId;
    private String mPassword;
    private String mNick;
    private Date mBirthday = new Date(946656000000L);
    private String mIntroduction = "这人懒的什么都没写下！";
    private int mGender = 1;
    private String mJob;
    private String mAddress;
    private String mLiveIntroduction = "来呀，直播间里面很好玩哦！";
    private String mAvatar;

    @Id
    @Column(name = "id")
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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
    @Column(name = "password")
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Basic
    @Column(name = "nick")
    public String getNick() {
        return mNick;
    }

    public void setNick(String nick) {
        mNick = nick;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        mBirthday = birthday;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
    }

    @Basic
    @Column(name = "gender")
    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    @Basic
    @Column(name = "job")
    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    @Basic
    @Column(name = "liveIntroduction")
    public String getLiveIntroduction() {
        return mLiveIntroduction;
    }

    public void setLiveIntroduction(String liveIntroduction) {
        mLiveIntroduction = liveIntroduction;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return mId == that.mId &&
                Objects.equals(mUserId, that.mUserId) &&
                Objects.equals(mPassword, that.mPassword) &&
                Objects.equals(mNick, that.mNick) &&
                Objects.equals(mBirthday, that.mBirthday) &&
                Objects.equals(mIntroduction, that.mIntroduction) &&
                Objects.equals(mGender, that.mGender) &&
                Objects.equals(mJob, that.mJob) &&
                Objects.equals(mAddress, that.mAddress) &&
                Objects.equals(mLiveIntroduction, that.mLiveIntroduction) &&
                Objects.equals(mAvatar, that.mAvatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mUserId, mPassword, mNick, mBirthday, mIntroduction, mGender, mJob, mAddress, mLiveIntroduction, mAvatar);
    }
}
