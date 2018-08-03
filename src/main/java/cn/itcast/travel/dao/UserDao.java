package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao
 * @date 2018/7/25
 */
public interface UserDao {
    User findUserByUsername(String username);
    Boolean Save(User user);

    void active(String username);

    User login(User user);

    User findByUid(int uid);
}
