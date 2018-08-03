package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.util.UUID;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service.impl
 * @date 2018/7/25
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();


    @Override
    public boolean register(User user) {

        User check = userDao.findUserByUsername(user.getUsername());
        if (check != null) {
            return false;
        }

        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.Save(user);

        MailUtils.sendMail(user.getEmail(),"<a href='http://localhost:8080/travel/user/active?code="+user.getCode()+"&username="+user.getUsername()+"'>点击激活</a>","黑马旅游网");
        return true;
    }

    @Override
    public boolean active(String code,String username) {
        if (code != null) {
            User user = userDao.findUserByUsername(username);
            if (code.equals(user.getCode())) {
                userDao.active(username);
                return true;
            }
        }
        return false;
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }
}
