package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.CityEntity;
import com.hongtao.live.dao.entity.CountryEntity;
import com.hongtao.live.dao.entity.ProvinceEntity;
import com.hongtao.live.module.Response;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created 2020/3/30.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @RequestMapping(value = "/getProvince", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<ProvinceEntity>> getProvince() {
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(ProvinceEntity.class);
        List<ProvinceEntity> provinceEntities = criteria.list();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ADDRESS_GET_SUCCESS, provinceEntities);
    }

    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<CityEntity>> getCity(@RequestParam("provinceId") int provinceId) {
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(CityEntity.class);
        criteria.add(Restrictions.eq("provinceId", provinceId));
        List<CityEntity> cityEntities = criteria.list();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ADDRESS_GET_SUCCESS, cityEntities);
    }

    @RequestMapping(value = "/getCountry", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<CountryEntity>> getCountry(@RequestParam("cityId") int cityId) {
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(CountryEntity.class);
        criteria.add(Restrictions.eq("cityId", cityId));
        List<CountryEntity> countryEntities = criteria.list();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_ADDRESS_GET_SUCCESS, countryEntities);
    }

}
