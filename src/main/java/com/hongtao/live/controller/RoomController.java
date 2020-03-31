package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.AttentionEntity;
import com.hongtao.live.dao.entity.RoomEntity;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.Response;
import com.hongtao.live.module.RoomData;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created 2020/3/18.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/checkRoom")
    @ResponseBody
    public Response<RoomData> checkRoom(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        logger.warn("userId = " + userId);
        Session session = Dao.getInstance().getSession();
        Query query =
                session.createQuery("from RoomEntity as r, UserEntity as u where r.userId = :userId and u.userId = :userId and u.userId = r.userId")
                        .setString("userId", userId)
                        .setString("userId", userId);
        List<Object> list = query.list();
        session.close();
        if (list.size() > 0) {
            RoomEntity roomEntity = (RoomEntity) ((Object[]) list.get(0))[0];
            UserEntity userEntity = (UserEntity) ((Object[]) list.get(0))[1];
            return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ROOM_GET_SUCCESS, RoomData.createRoom(Content.Code.CODE_ROOM_EXIST, roomEntity, userEntity));
        } else {
            Session session1 = Dao.getInstance().getSession();
            Criteria criteria = session1.createCriteria(UserEntity.class);
            criteria.add(Restrictions.eq("userId", userId));
            List<UserEntity> users = criteria.list();
            session1.close();
            return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ROOM_GET_FAIL, RoomData.createNullRoom(users.get(0).getNick(), users.get(0).getAvatar()));
        }
    }

    @RequestMapping(value = "/createRoom", method = RequestMethod.POST)
    @ResponseBody
    public Response<RoomData> createRoom(HttpServletRequest request, @RequestParam String roomName, @RequestParam String roomIntroduction) {
        String userId = (String) request.getAttribute("userId");
        logger.warn("roomName = " + roomName + "    roomIntroduction = " + roomIntroduction);

        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomName(roomName);
        roomEntity.setRoomIntroduction(roomIntroduction);
        roomEntity.setLiving(0);
        roomEntity.setNum(0);
        roomEntity.setUserId(userId);
        roomEntity.setUrl("rtmp://" + Content.IP + ":1935/Live/" + userId);
        session.save(roomEntity);

        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        List<UserEntity> users = criteria.list();

        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ROOM_CREATE_SUCCESS, RoomData.createRoom(Content.Code.CODE_ROOM_CREATE, roomEntity, users.get(0)));
    }

    @RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
    @ResponseBody
    public Response<RoomData> updateRoom(HttpServletRequest request, @RequestParam int roomId, @RequestParam String roomName, @RequestParam String roomIntroduction) {
        String userId = (String) request.getAttribute("userId");
        logger.warn("roomName = " + roomName + "    roomIntroduction = " + roomIntroduction);

        Session session = Dao.getInstance().getSession();
        session.beginTransaction();
        RoomEntity roomEntity = session.get(RoomEntity.class, roomId);
        roomEntity.setRoomName(roomName);
        roomEntity.setRoomIntroduction(roomIntroduction);
        roomEntity.setLiving(0);
        roomEntity.setNum(0);
        session.update(roomEntity);

        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        List<UserEntity> users = criteria.list();

        session.getTransaction().commit();
        session.close();


        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ROOM_UPDATE_SUCCESS, RoomData.createRoom(Content.Code.CODE_ROOM_UPDATE, roomEntity, users.get(0)));
    }

    @RequestMapping("/getRooms")
    @ResponseBody
    public Response<List<RoomData>> getRooms() {
        Session session = Dao.getInstance().getSession();
        Query query =
                session.createQuery("from RoomEntity as r, UserEntity as u where u.userId = r.userId and r.living = 1");
        List<Object> list = query.list();
        List<RoomData> roomData = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RoomEntity roomEntity = (RoomEntity) ((Object[]) list.get(i))[0];
            UserEntity userEntity = (UserEntity) ((Object[]) list.get(i))[1];
            roomData.add(RoomData.createRoom(Content.Code.CODE_ROOM_EXIST, roomEntity, userEntity));
        }
        for (int i = 0; i < roomData.size(); i++) {
            Criteria criteria = session.createCriteria(AttentionEntity.class);
            criteria.add(Restrictions.eq("roomId", roomData.get(i).getRoomId()));
            roomData.get(i).setAttention(criteria.list().size() > 0);
        }
        session.close();
        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ROOM_GET_SUCCESS, roomData);
    }

    @RequestMapping("/searchRooms")
    @ResponseBody
    public Response<List<RoomData>> searchRooms(@RequestParam String roomKey) {
        System.out.println(roomKey);
        Session session = Dao.getInstance().getSession();
        Query query =
                session.createQuery("from RoomEntity as r, UserEntity as u where u.userId = r.userId and r.living = 1 and u.nick like '%" + roomKey + "%'");
        List<Object> list = query.list();
        List<RoomData> roomData = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RoomEntity roomEntity = (RoomEntity) ((Object[]) list.get(i))[0];
            UserEntity userEntity = (UserEntity) ((Object[]) list.get(i))[1];
            roomData.add(RoomData.createRoom(Content.Code.CODE_ROOM_EXIST, roomEntity, userEntity));
        }
        for (int i = 0; i < roomData.size(); i++) {
            Criteria criteria = session.createCriteria(AttentionEntity.class);
            criteria.add(Restrictions.eq("roomId", roomData.get(i).getRoomId()));
            roomData.get(i).setAttention(criteria.list().size() > 0);
        }
        session.close();
        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ROOM_GET_SUCCESS, roomData);
    }
}
