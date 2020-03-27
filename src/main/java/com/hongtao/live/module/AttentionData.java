package com.hongtao.live.module;

import com.hongtao.live.dao.entity.AttentionEntity;
import com.hongtao.live.dao.entity.RoomEntity;
import com.hongtao.live.dao.entity.UserEntity;

import java.sql.Timestamp;

/**
 * Created 2020/3/26.
 *
 * @author HongTao
 */
public class AttentionData {
    private String userId;
    private String avatar;
    private String nick;
    private int gender;
    private int roomId;
    private String url;
    private int num;
    private String roomName;
    private String roomIntroduction;
    private boolean isLiving;
    private int attentionId;
    private Timestamp time;

    public static AttentionData create(AttentionEntity attentionEntity, RoomEntity roomEntity, UserEntity userEntity) {
        AttentionData attentionData = new AttentionData();
        attentionData.setUserId(userEntity.getUserId());
        attentionData.setAvatar(userEntity.getAvatar());
        attentionData.setNick(userEntity.getNick());
        attentionData.setGender(userEntity.getGender());
        attentionData.setRoomId(roomEntity.getRoomId());
        attentionData.setUrl(roomEntity.getUrl());
        attentionData.setNum(roomEntity.getNum());
        attentionData.setRoomName(roomEntity.getRoomName());
        attentionData.setRoomIntroduction(roomEntity.getRoomIntroduction());
        attentionData.setLiving(roomEntity.getLiving() == 1);
        attentionData.setAttentionId(attentionEntity.getAttentionId());
        attentionData.setTime(attentionEntity.getTime());

        return attentionData;
    }

    @Override
    public String toString() {
        return "AttentionData{" +
                "userId='" + userId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nick='" + nick + '\'' +
                ", gender=" + gender +
                ", roomId=" + roomId +
                ", url='" + url + '\'' +
                ", num=" + num +
                ", roomName='" + roomName + '\'' +
                ", roomIntroduction='" + roomIntroduction + '\'' +
                ", isLiving=" + isLiving +
                ", attentionId=" + attentionId +
                ", time=" + time +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomIntroduction() {
        return roomIntroduction;
    }

    public void setRoomIntroduction(String roomIntroduction) {
        this.roomIntroduction = roomIntroduction;
    }

    public int getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(int attentionId) {
        this.attentionId = attentionId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean isLiving() {
        return isLiving;
    }

    public void setLiving(boolean living) {
        isLiving = living;
    }
}
