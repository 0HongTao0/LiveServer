package com.hongtao.live.module;

import com.hongtao.live.Content;
import com.hongtao.live.dao.entity.RoomEntity;
import com.hongtao.live.dao.entity.UserEntity;

/**
 * Created 2020/3/18.
 *
 * @author HongTao
 */
public class RoomData {
    private int code; //1 存在可开始直播，-1 不存在需要创建
    private int mRoomId;
    private int mNum;
    private String mUserId;
    private String mUrl;
    private int mLiving;
    private String mRoomName;
    private String mRoomIntroduction;
    private String avatar;
    private String mNick;
    private boolean isAttention;

    public RoomData(int code, int roomId, int num, String userId, String url, int living, String roomName, String roomIntroduction, String avatar) {
        this.code = code;
        mRoomId = roomId;
        mNum = num;
        mUserId = userId;
        mUrl = url;
        mLiving = living;
        mRoomName = roomName;
        mRoomIntroduction = roomIntroduction;
        this.avatar = avatar;
    }

    public RoomData(int code, int roomId, int num, String userId, String nick, String url, int living, String roomName, String roomIntroduction, String avatar) {
        this.code = code;
        mRoomId = roomId;
        mNum = num;
        mUserId = userId;
        mNick = nick;
        mUrl = url;
        mLiving = living;
        mRoomName = roomName;
        mRoomIntroduction = roomIntroduction;
        this.avatar = avatar;
    }

    public RoomData(int code, int roomId, int num, String userId, String url, int living, String roomName, String roomIntroduction, String avatar, String nick, boolean isAttention) {
        this.code = code;
        mRoomId = roomId;
        mNum = num;
        mUserId = userId;
        mUrl = url;
        mLiving = living;
        mRoomName = roomName;
        mRoomIntroduction = roomIntroduction;
        this.avatar = avatar;
        mNick = nick;
        this.isAttention = isAttention;
    }

    public RoomData() {
    }

    public static RoomData createRoom(int code, RoomEntity roomEntity, UserEntity userEntity) {
        RoomData roomData = new RoomData();

        roomData.setCode(code);
        roomData.setRoomId(roomEntity.getRoomId());
        roomData.setNum(roomEntity.getNum());
        roomData.setUserId(roomEntity.getUserId());
        roomData.setUrl(roomEntity.getUrl());
        roomData.setLiving(roomEntity.getLiving());
        roomData.setRoomName(roomEntity.getRoomName());
        roomData.setRoomIntroduction(roomEntity.getRoomIntroduction());
        roomData.setAvatar(userEntity.getAvatar());
        roomData.setNick(userEntity.getNick());

        return roomData;
    }

    public static RoomData createNullRoom(String nick, String avatar) {
        return new RoomData(Content.Code.CODE_ROOM_NOT_EXIST, 0, 0, "", nick, 0, "", "", "", avatar, false);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getRoomId() {
        return mRoomId;
    }

    public void setRoomId(int roomId) {
        mRoomId = roomId;
    }

    public int getNum() {
        return mNum;
    }

    public void setNum(int num) {
        mNum = num;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getLiving() {
        return mLiving;
    }

    public void setLiving(int living) {
        mLiving = living;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String roomName) {
        mRoomName = roomName;
    }

    public String getRoomIntroduction() {
        return mRoomIntroduction;
    }

    public void setRoomIntroduction(String roomIntroduction) {
        mRoomIntroduction = roomIntroduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return mNick;
    }

    public void setNick(String nick) {
        mNick = nick;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }
}
