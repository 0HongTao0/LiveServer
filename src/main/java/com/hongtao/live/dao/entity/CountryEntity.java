package com.hongtao.live.dao.entity;

import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/30.
 *
 * @author HongTao
 */
@Entity
@Table(name = "country", schema = "Live", catalog = "Live")
public class CountryEntity {
    private int mId;
    private String mName;
    private int mCityId;
    private int mProvinceId;

    @Id
    @Column(name = "id")
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Basic
    @Column(name = "cityId")
    public int getCityId() {
        return mCityId;
    }

    public void setCityId(int cityId) {
        mCityId = cityId;
    }

    @Basic
    @Column(name = "provinceId")
    public int getProvinceId() {
        return mProvinceId;
    }

    public void setProvinceId(int provinceId) {
        mProvinceId = provinceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return mId == that.mId &&
                mCityId == that.mCityId &&
                mProvinceId == that.mProvinceId &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName, mCityId, mProvinceId);
    }
}
