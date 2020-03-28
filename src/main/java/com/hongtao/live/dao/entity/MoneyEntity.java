package com.hongtao.live.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/28.
 *
 * @author HongTao
 */
@Entity
@Table(name = "money", schema = "Live", catalog = "Live")
public class MoneyEntity implements Serializable {
    private static final long serialVersionUID = 0L;
    private int mId;
    private String mUserId;
    private double mMoney;
    private int mType;
    private Timestamp mTime;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
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
    @Column(name = "money")
    public double getMoney() {
        return mMoney;
    }

    public void setMoney(double money) {
        mMoney = money;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
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
        MoneyEntity that = (MoneyEntity) o;
        return mId == that.mId &&
                Double.compare(that.mMoney, mMoney) == 0 &&
                mType == that.mType &&
                Objects.equals(mUserId, that.mUserId) &&
                Objects.equals(mTime, that.mTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mUserId, mMoney, mType, mTime);
    }
}
