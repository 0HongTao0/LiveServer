package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.Response;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created 2020/3/17.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/me")
public class MeController {
    private Logger logger =  LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/user", method = {RequestMethod.GET})
    @ResponseBody
    public Response<UserEntity> getUser(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        logger.warn("userId = " + userId);
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        List<UserEntity> users = criteria.list();
        logger.warn(users.get(0).toString());
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_GET_SUCCESS, users.get(0));
    }
}
