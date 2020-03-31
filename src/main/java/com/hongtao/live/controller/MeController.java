package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.CityEntity;
import com.hongtao.live.dao.entity.CountryEntity;
import com.hongtao.live.dao.entity.ProvinceEntity;
import com.hongtao.live.dao.entity.UserEntity;
import com.hongtao.live.module.AlterAvatarData;
import com.hongtao.live.module.NormalResponseData;
import com.hongtao.live.module.Response;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/alterAvatar", method = RequestMethod.POST)
    @ResponseBody
    // 这里的MultipartFile对象变量名跟表单中的file类型的input标签的name相同，所以框架会自动用MultipartFile对象来接收上传过来的文件，当然也可以使用@RequestParam("img")指定其对应的参数名称
    public Response<AlterAvatarData> upload(HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar, HttpSession session)
            throws Exception {
        String userId = (String) request.getAttribute("userId");
        // 如果没有文件上传，MultipartFile也不会为null，可以通过调用getSize()方法获取文件的大小来判断是否有上传文件
        if (avatar.getSize() > 0) {
            // 得到项目在服务器的真实根路径，如：/home/tomcat/webapp/项目名/images
            String path = session.getServletContext().getRealPath("pic");
            // 得到文件的原始名称，如：美女.png
            String fileName = avatar.getOriginalFilename();
            // 通过文件的原始名称，可以对上传文件类型做限制，如：只能上传jpg和png的图片文件
            if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
                File file = new File(path, fileName);
                avatar.transferTo(file);

                String avatarUrl = "http://" + Content.IP + ":8080/pic/" + fileName;

                Session daoSession = Dao.getInstance().getSession();
                Transaction transaction = daoSession.beginTransaction();
                UserEntity userEntity = (UserEntity) daoSession.createCriteria(UserEntity.class).add(Restrictions.eq("userId", userId)).uniqueResult();
                userEntity.setAvatar(avatarUrl);
                daoSession.saveOrUpdate(userEntity);
                transaction.commit();
                daoSession.close();

                return new Response<>(Response.CODE_SUCCESS, AlterAvatarData.MSG_AVATAR_ALTER_SUCCESS, new AlterAvatarData(avatarUrl, AlterAvatarData.CODE_AVATAR_ALTER_SUCCESS));
            } else {
                return new Response<>(Response.CODE_SUCCESS, AlterAvatarData.MSG_AVATAR_FORMATE_ERROR, new AlterAvatarData("", AlterAvatarData.CODE_AVATAR_FORMAT_ERROR));
            }
        }
        return new Response<>(Response.CODE_SUCCESS, AlterAvatarData.MSG_AVATAR_ALTER_FAIl, new AlterAvatarData("", AlterAvatarData.CODE_AVATAR_ALTER_FAIL));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public Response<AlterAvatarData> handle(MaxUploadSizeExceededException ex) throws Exception {
        return new Response<>(Response.CODE_SUCCESS, AlterAvatarData.MSG_AVATAR_TOO_LARGE, new AlterAvatarData("", AlterAvatarData.CODE_AVATAR_TOO_LARGE));
    }

}
