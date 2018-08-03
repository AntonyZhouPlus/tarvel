package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service
 * @date 2018/7/28
 */
public interface CategoryService {
    List<Category> findAll();
}
