package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.AttentionEntity;
import com.hongtao.live.dao.entity.RoomEntity;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.AttentionData;
import com.hongtao.live.module.NormalResponseData;
import com.hongtao.live.module.Response;

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
 * Created 2020/3/26.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/attention")
public class AttentionControl {

    @RequestMapping(value = "/attentionRoom", method = RequestMethod.POST)
    @ResponseBody
    public Response<NormalResponseData> attentionRoom(HttpServletRequest request, @RequestParam int roomId) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        AttentionEntity attentionEntity = new AttentionEntity();
        attentionEntity.setUserId(userId);
        attentionEntity.setRoomId(roomId);
        attentionEntity.setTime(new Timestamp(System.currentTimeMillis()));
        session.save(attentionEntity);
        transaction.commit();
        session.close();
        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ATTENTION_ROOM_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/getOffRoom", method = RequestMethod.POST)
    @ResponseBody
    public Response<NormalResponseData> getOffRoom(HttpServletRequest request, @RequestParam int roomId) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete Attention where roomId = :roomId and userId = :userId")
                .setInteger("roomId", roomId)
                .setString("userId", userId);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_GET_OFF_ATTENTION_ROOM_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping("/getAttentionRoom")
    @ResponseBody
    public Response<List<AttentionData>> getAttentionRoom(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Query query =
                session.createQuery("from  AttentionEntity as a ,RoomEntity as r ,UserEntity as u  where a.userId = :userId and a.roomId = r.roomId and r.userId = u.userId")
                        .setString("userId", userId);
        List<Object> list = query.list();
        System.out.println(list.toString());
        session.close();
        List<AttentionData> attentionDataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AttentionEntity attentionEntity = (AttentionEntity) ((Object[]) list.get(i))[0];
            RoomEntity roomEntity = (RoomEntity) ((Object[]) list.get(i))[1];
            UserEntity userEntity = (UserEntity) ((Object[]) list.get(i))[2];
            attentionDataList.add(AttentionData.create(attentionEntity, roomEntity, userEntity));
        }

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_GET_ATTENTION_ROOM_SUCCESS, attentionDataList);
    }

}
