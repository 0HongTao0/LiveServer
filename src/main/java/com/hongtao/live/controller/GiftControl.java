package com.hongtao.live.controller;

import com.hongtao.live.Content;
import com.hongtao.live.dao.Dao;
import com.hongtao.live.dao.entity.*;
import com.hongtao.live.module.NormalResponseData;
import com.hongtao.live.module.Response;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
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
 * Created 2020/3/27.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/gift")
public class GiftControl {
    /**
     * 礼物抽成
     */
    private static final float PROPORTIONATE = 0.2f;

    @RequestMapping("/getGifts")
    @ResponseBody
    public Response<List<GiftEntity>> getGifts() {
        Session session = Dao.getInstance().getSession();
        Criteria criteria = session.createCriteria(GiftEntity.class);
        List<GiftEntity> giftEntities = criteria.list();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_GIFT_GET_SUCCESS, giftEntities);
    }

    @RequestMapping(value = "/sendGift", method = RequestMethod.POST)
    @ResponseBody
    public Response<NormalResponseData> sendGift(
            HttpServletRequest request
            , @RequestParam int giftId
            , @RequestParam String toUserId
            , @RequestParam int roomId) {
        String userId = (String) request.getAttribute("userId");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Session session = Dao.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteriaGift = session.createCriteria(GiftEntity.class);
        criteriaGift.add(Restrictions.eq("giftId", giftId));
        List<GiftEntity> giftEntities = criteriaGift.list();

        Criteria fromUserCriteria = session.createCriteria(UserEntity.class);
        fromUserCriteria.add(Restrictions.eq("userId", userId));
        List<UserEntity> fromUserEntities = fromUserCriteria.list();

        if (fromUserEntities.get(0).getMoney() < giftEntities.get(0).getPrice()) {
            transaction.commit();
            session.close();
            return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_GIFT_SEND_FAIL, new NormalResponseData(NormalResponseData.CODE_FAIL));
        }

        GiftSendEntity giftSendEntity = new GiftSendEntity();
        giftSendEntity.setFromUserId(userId);
        giftSendEntity.setGiftId(giftId);
        giftSendEntity.setToUserId(toUserId);
        giftSendEntity.setRoomId(roomId);
        giftSendEntity.setTime(timestamp);
        session.save(giftSendEntity);

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setType(2);
        chatEntity.setRoomId(roomId);
        chatEntity.setUserId(userId);
        chatEntity.setTime(timestamp);
        chatEntity.setMessage("送出一份 \"" + giftEntities.get(0).getName() + "\" 礼物！！!");
        session.save(chatEntity);

        UserEntity fromUserEntity = session.get(UserEntity.class, fromUserEntities.get(0).getId());
        fromUserEntity.setMoney(fromUserEntity.getMoney() - giftEntities.get(0).getPrice());
        session.saveOrUpdate(fromUserEntity);

        Criteria toUserCriteria = session.createCriteria(UserEntity.class);
        toUserCriteria.add(Restrictions.eq("userId", toUserId));
        List<UserEntity> toUserEntities = toUserCriteria.list();
        UserEntity toUserEntity = toUserEntities.get(0);
        toUserEntity.setMoney(toUserEntity.getMoney() + giftEntities.get(0).getPrice() * (1 - PROPORTIONATE));
        session.saveOrUpdate(toUserEntity);

        int id = (int) session.createCriteria(MoneyRecordEntity.class)
                .setProjection(Projections.projectionList().add(Projections.max("id"))).uniqueResult();

        MoneyRecordEntity fromMoneyEntity = new MoneyRecordEntity();
        fromMoneyEntity.setId(id + 1);
        fromMoneyEntity.setUserId(userId);
        fromMoneyEntity.setType(-2);
        fromMoneyEntity.setTime(timestamp);
        fromMoneyEntity.setMoney(giftEntities.get(0).getPrice());
        session.persist(fromMoneyEntity);

        MoneyRecordEntity toMoneyEntity = new MoneyRecordEntity();
        toMoneyEntity.setId(id + 2);
        toMoneyEntity.setUserId(toUserId);
        toMoneyEntity.setType(2);
        toMoneyEntity.setTime(timestamp);
        toMoneyEntity.setMoney(giftEntities.get(0).getPrice() * (1 - PROPORTIONATE));
        session.persist(toMoneyEntity);

        transaction.commit();
        session.close();

        return new Response<>(Response.CODE_SUCCESS, Content.Message.MSG_GIFT_SEND_SUCCESS, new NormalResponseData(NormalResponseData.CODE_SUCCESS));
    }
}
