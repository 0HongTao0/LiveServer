package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.MoneyEntity;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.NormalResponseData;
import com.hongtao.live.module.Response;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created 2020/3/28.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/money")
public class MoneyController {

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public Response<NormalResponseData> recharge(
            HttpServletRequest request,
            @RequestParam("money") float money) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        List<UserEntity> userEntities = criteria.list();
        UserEntity userEntity = userEntities.get(0);
        userEntity.setMoney(userEntity.getMoney() + money);
        session.saveOrUpdate(userEntity);

        MoneyEntity moneyEntity = new MoneyEntity();
        moneyEntity.setMoney(money);
        moneyEntity.setTime(new Timestamp(System.currentTimeMillis()));
        moneyEntity.setType(1);
        moneyEntity.setUserId(userId);
        session.save(moneyEntity);

        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_MONEY_RECHARGE_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ResponseBody
    public Response<NormalResponseData> withdraw(
            HttpServletRequest request,
            @RequestParam("money") float money) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        List<UserEntity> userEntities = criteria.list();
        UserEntity userEntity = userEntities.get(0);
        if (userEntity.getMoney() < money) {
            transaction.commit();
            session.close();
            return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_MONEY_WITHDRAW_FAIL, new NormalResponseData(NormalResponseData.CODE_FAIL));
        }
        userEntity.setMoney(userEntity.getMoney() - money);
        session.saveOrUpdate(userEntity);


        MoneyEntity moneyEntity = new MoneyEntity();
        moneyEntity.setMoney(money);
        moneyEntity.setTime(new Timestamp(System.currentTimeMillis()));
        moneyEntity.setType(-1);
        moneyEntity.setUserId(userId);
        session.save(moneyEntity);

        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_MONEY_WITHDRAW_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

}
