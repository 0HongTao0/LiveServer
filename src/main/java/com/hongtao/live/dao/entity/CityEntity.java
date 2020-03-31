package com.hongtao.live.dao.entity;

import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/30.
 *
 * @author HongTao
 */
@Entity
@Table(name = "city", schema = "Live", catalog = "Live")
public class CityEntity {
    private int mId;
    private String mName;
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
        CityEntity that = (CityEntity) o;
        return mId == that.mId &&
                mProvinceId == that.mProvinceId &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName, mProvinceId);
    }
}
