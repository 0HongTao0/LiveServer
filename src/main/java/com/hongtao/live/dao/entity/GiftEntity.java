package com.hongtao.live.dao.entity;

import java.util.Objects;

import javax.persistence.*;

/**
 * Created 2020/3/27.
 *
 * @author HongTao
 */
@Entity
@Table(name = "gift", schema = "Live", catalog = "Live")
public class GiftEntity {
    private int mGiftId;
    private double mPrice;
    private String mName;
    private String mPic;

    @Id
    @Column(name = "giftId")
    public int getGiftId() {
        return mGiftId;
    }

    public void setGiftId(int giftId) {
        mGiftId = giftId;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
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
    @Column(name = "pic")
    public String getPic() {
        return mPic;
    }

    public void setPic(String pic) {
        mPic = pic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftEntity that = (GiftEntity) o;
        return mGiftId == that.mGiftId &&
                Double.compare(that.mPrice, mPrice) == 0 &&
                Objects.equals(mName, that.mName) &&
                Objects.equals(mPic, that.mPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mGiftId, mPrice, mName, mPic);
    }
}
