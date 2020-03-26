package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.ChatEntity;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.MessageData;
import com.hongtao.live.module.Response;
import com.hongtao.live.module.NormalResponseData;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created 2020/3/25.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/chat")
public class ChatControl {

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public Response<NormalResponseData> sendMessage(HttpServletRequest request
            , @RequestParam int roomId
            , @RequestParam String message
            , @RequestParam int type) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setUserId(userId);
        chatEntity.setRoomId(roomId);
        chatEntity.setMessage(message);
        chatEntity.setType(type);
        chatEntity.setTime(new Timestamp(System.currentTimeMillis()));
        session.save(chatEntity);
        transaction.commit();
        session.close();
        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_MESSAGE_SEND_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    @ResponseBody
    public Response<List<MessageData>> sendMessage(@RequestParam int roomId) {
        Session session = Dao.getInstance().getSession();
        Query query =
                session.createQuery("from  ChatEntity as c, UserEntity as u where c.roomId = :roomId and c.userId = u.userId")
                        .setInteger("roomId", roomId);
        List<Object> list = query.list();
        session.close();
        List<MessageData> messageData = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ChatEntity chatEntity = (ChatEntity) ((Object[]) list.get(i))[0];
            UserEntity userEntity = (UserEntity) ((Object[]) list.get(i))[1];
            messageData.add(MessageData.create(chatEntity, userEntity));
        }
        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_MESSAGE_GET_SUCCESS, messageData);
    }


}
