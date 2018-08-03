package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao.impl
 * @date 2018/7/25
 */
public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByUsername(String username) {
        User user = null;

        try {
            String sql = "select * from tab_user where username = ?";

            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class) , username);

        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public Boolean Save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values (?,?,?,?,?,?,?,?,?)";
        int update = template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
        if (update == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void active(String username) {
        String sql = "update tab_user set status = 'Y' where username = ?";
        template.update(sql,username);
    }

    @Override
    public User login(User user) {
        User login_user = null;

        try {
            String sql = "select * from tab_user where username = ?";
            login_user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getUsername());
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return login_user;
    }

    @Override
    public User findByUid(int uid) {
        User user = null;

        try {
            String sql = "select * from tab_user where uid = ?";

            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class) , uid);

        } catch (DataAccessException e) {

        }
        return user;
    }
}
