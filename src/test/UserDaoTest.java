import com.hongtao.live.dao.UserEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

/**
 * Created 2020/3/13.
 *
 * @author HongTao
 */
public class UserDaoTest {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction tx = null;
    @Before
    public void init() {
        config = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }
    //增加
    @Test
    public void insert() {
        UserEntity ue = new UserEntity();
        ue.setName("Anny");
        ue.setUserId("123");
        ue.setBirthday(new Date(System.currentTimeMillis()));
        session.save(ue);
        tx.commit();
    }
    //修改
    @Test
    public void update() {
        UserEntity user = (UserEntity) session.get(UserEntity.class, new Integer(2));
        user.setName("Penny");
        session.update(user);
        tx.commit();
        session.close();
    }
    //查找
    @Test
    public void getById() {
        UserEntity user = (UserEntity) session.get(UserEntity.class, new Integer(8));
        tx.commit();
        session.close();
        System.out.println("ID号：" + user.getId() + "；用户名：" + user.getName() +
                "；UserId：" + user.getUserId() + "；生日：" + user.getBirthday());
    }
    //删除
    @Test
    public void delete() {
        UserEntity user = (UserEntity) session.get(UserEntity.class, new Integer(6));
        session.delete(user);
        tx.commit();
        session.close();
    }
}