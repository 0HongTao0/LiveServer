package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.LoginData;
import com.hongtao.live.module.Response;
import com.hongtao.live.util.JwtUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created 2020/3/14.
 *
 * @author HongTao
 */

@Controller
@RequestMapping("/loginAction")
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public Response<LoginData> login(@RequestParam String userId, @RequestParam String password) {
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("password", password));
        List<UserEntity> users = criteria.list();
        session.close();

        if (users.size() != 0) {
            String token = JwtUtil.createJwt(userId);
            return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_LOGIN_SUCCESS, new LoginData(token, Content.Code.CODE_LOGIN_SUCCESS));
        } else {
            return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_LOGIN_FAIL, new LoginData("", Content.Code.CODE_LOGIN_FAIL));
        }
    }

    @RequestMapping(value = "/registered", method = {RequestMethod.POST})
    @ResponseBody
    public Response<LoginData> registered(@RequestParam String userId, @RequestParam String password) {
        Session session1 = Dao.getInstance().getSession();
        Criteria criteria = session1.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("password", password));
        List<UserEntity> users = criteria.list();
        if (users.size() != 0) {
            return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_REGISTERED_FAIL_SAME_ID, new LoginData("",Content.Code.CODE_REGISTERED_FAIL_SAME_ID));
        }
        session1.close();

        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = new UserEntity();
        userEntity.setNick(userId);
        userEntity.setUserId(userId);
        userEntity.setPassword(password);
        session.save(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_REGISTERED_SUCCESS, new LoginData("", Content.Code.CODE_REGISTERED_SUCCESS));
    }
}
