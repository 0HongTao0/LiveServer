package com.hongtao.live.module;

import com.hongtao.live.dao.entity.ChatEntity;
import com.hongtao.live.dao.entity.UserEntity;

import java.sql.Timestamp;

/**
 * Created 2020/3/25.
 *
 * @author HongTao
 */
public class MessageData {
    private String avatar;
    private String nick;
    private String message;
    private Timestamp time;
    private int type;

    public static MessageData create(ChatEntity chatEntity, UserEntity userEntity) {
        MessageData messageData = new MessageData();

        messageData.setAvatar(userEntity.getAvatar());
        messageData.setNick(userEntity.getNick());
        messageData.setMessage(chatEntity.getMessage());
        messageData.setTime(chatEntity.getTime());
        messageData.setType(chatEntity.getType());

        return messageData;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
