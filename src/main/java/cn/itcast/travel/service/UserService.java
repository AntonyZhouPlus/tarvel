package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service
 * @date 2018/7/25
 */
public interface UserService {
    boolean register(User user);

    boolean active(String code,String username);

    User login(User user);
}
