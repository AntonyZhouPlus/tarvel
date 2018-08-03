package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao.impl
 * @date 2018/7/28
 */
public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM tab_category;";
        List<Category> list = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return list;
    }
}
