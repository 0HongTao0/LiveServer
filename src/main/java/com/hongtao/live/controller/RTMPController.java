package com.hongtao.live.controller;

import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.RoomEntity;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created 2020/3/18.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/rtmp")
public class RTMPController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/on_connect")
    @ResponseBody
    public String connect(@RequestParam(value = "name") String name) {
        logger.warn(name);

        return "on_connect";
    }


    @RequestMapping(value = "/on_publish")
    @ResponseBody
    public String publish(@RequestParam(value = "name") String name) {
        logger.warn(name);
        changeLiving(name, true);
        return "on_publish";
    }

    @RequestMapping(value = "/on_publish_done")
    @ResponseBody
    public String publishDone(@RequestParam(value = "name") String name) {
        logger.warn(name);
        changeLiving(name, false);
        return "on_publish_done";
    }

    @RequestMapping(value = "/on_play")
    @ResponseBody
    public String play(@RequestParam(value = "name") String name) {
        logger.warn(name);
        changeNum(name, 1);
        return "on_play";
    }

    @RequestMapping(value = "/on_play_done")
    @ResponseBody
    public String playDone(@RequestParam(value = "name") String name) {
        logger.warn(name);
        changeNum(name, -1);
        return "on_play_done";
    }

    private void changeLiving(String userId, boolean isLiving) {
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(RoomEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        RoomEntity roomEntities = (RoomEntity) criteria.list().get(0);
        session.close();

        Session session1 = Dao.getInstance().getSession();
        session1.beginTransaction();
        roomEntities.setLiving(isLiving ? 1 : 0);
        session1.update(roomEntities);
        session1.getTransaction().commit();
        session1.close();
    }

    private void changeNum(String userId, int addNum) {
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(RoomEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        RoomEntity roomEntities = (RoomEntity) criteria.list().get(0);
        session.close();

        Session session1 = Dao.getInstance().getSession();
        session1.beginTransaction();
        roomEntities.setNum(roomEntities.getNum() + addNum);
        session1.update(roomEntities);
        session1.getTransaction().commit();
        session1.close();
    }

}
