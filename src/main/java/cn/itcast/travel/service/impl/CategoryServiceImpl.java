package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service.impl
 * @date 2018/7/28
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 此方法从数据库查询目录列表返回到前端
     * @return
     */
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> zset = jedis.zrangeWithScores("category", 0, -1);
        List<Category> list = null;

        if (zset == null || zset.size() == 0) {
//            System.out.println("从mysql获取");
            list = categoryDao.findAll();
            for (Category category : list) {
                jedis.zadd("category", category.getCid(), category.getCname());
            }
        } else {
//            System.out.println("从redis获取");
            list = new ArrayList<Category>();
            for (Tuple tuple : zset) {
                Category category = new Category();
                category.setCid((int)tuple.getScore());
                category.setCname(tuple.getElement());
                list.add(category);
            }
        }
        jedis.close();
        return list;
    }
}
