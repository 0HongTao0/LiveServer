package com.hongtao.live.dao.entity;

import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/30.
 *
 * @author HongTao
 */
@Entity
@Table(name = "province", schema = "Live", catalog = "Live")
public class ProvinceEntity {
    private int mId;
    private String mName;
    private String mCode;

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
    @Column(name = "code")
    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceEntity that = (ProvinceEntity) o;
        return mId == that.mId &&
                Objects.equals(mName, that.mName) &&
                Objects.equals(mCode, that.mCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName, mCode);
    }
}
