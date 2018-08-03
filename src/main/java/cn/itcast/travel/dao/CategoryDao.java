package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao.impl
 * @date 2018/7/28
 */
public interface CategoryDao {
    List<Category> findAll();
}
