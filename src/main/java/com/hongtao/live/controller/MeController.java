package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.CityEntity;
import com.hongtao.live.dao.entity.CountryEntity;
import com.hongtao.live.dao.entity.ProvinceEntity;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.NormalResponseData;
import com.hongtao.live.module.Response;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
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
    private Logger logger = LoggerFactory.getLogger(getClass());

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

    @RequestMapping(value = "/alterNick", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterNick(HttpServletRequest request, @RequestParam("nick") String nick) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        userEntity.setNick(nick);
        session.saveOrUpdate(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/alterGender", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterGender(HttpServletRequest request, @RequestParam("gender") int gender) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        userEntity.setGender(gender);
        session.saveOrUpdate(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/alterBirthday", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterBirthday(HttpServletRequest request, @RequestParam("birthday") long birthday) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        userEntity.setBirthday(new Date(birthday));
        session.saveOrUpdate(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/alterJob", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterJob(HttpServletRequest request, @RequestParam("job") String job) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        userEntity.setJob(job);
        session.saveOrUpdate(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/alterAddress", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterAddress(HttpServletRequest request, @RequestParam("addressId") int addressId) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria userCriteria = session.createCriteria(UserEntity.class);
        userCriteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) userCriteria.uniqueResult();

        StringBuilder addressStringBuilder = new StringBuilder();

        Criteria countryCriteria = session.createCriteria(CountryEntity.class);
        countryCriteria.add(Restrictions.eq("id", addressId));
        CountryEntity countryEntity = (CountryEntity) countryCriteria.uniqueResult();

        Criteria cityCriteria = session.createCriteria(CityEntity.class);
        cityCriteria.add(Restrictions.eq("id", countryEntity.getCityId()));
        CityEntity cityEntity = (CityEntity) cityCriteria.uniqueResult();

        Criteria provinceCriteria = session.createCriteria(ProvinceEntity.class);
        provinceCriteria.add(Restrictions.eq("id", cityEntity.getProvinceId()));
        ProvinceEntity provinceEntity = (ProvinceEntity) provinceCriteria.uniqueResult();

        addressStringBuilder.append(provinceEntity.getName()).append("-");
        addressStringBuilder.append(cityEntity.getName()).append("-");
        addressStringBuilder.append(countryEntity.getName());

        userEntity.setAddressId(addressId);
        userEntity.setAddress(addressStringBuilder.toString());
        System.out.println(userEntity.toString());

        session.update(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }


    @RequestMapping(value = "/alterIntroduce", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterIntroduce(HttpServletRequest request, @RequestParam("introduce") String introduce) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        userEntity.setIntroduction(introduce);
        session.saveOrUpdate(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/alterLiveIntroduce", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterLiveIntroduce(HttpServletRequest request, @RequestParam("liveIntroduce") String liveIntroduce) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        userEntity.setLiveIntroduction(liveIntroduce);
        session.saveOrUpdate(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

    @RequestMapping(value = "/alterPassword", method = {RequestMethod.POST})
    @ResponseBody
    public Response<NormalResponseData> alterPassword(HttpServletRequest request, @RequestParam("password") String password) {
        String userId = (String) request.getAttribute("userId");
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        userEntity.setPassword(password);
        session.saveOrUpdate(userEntity);
        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ME_ALTER_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }

}
