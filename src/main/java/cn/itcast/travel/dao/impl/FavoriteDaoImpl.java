package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao.impl
 * @date 2018/8/1
 */
public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findFavoriteByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "SELECT * FROM tab_favorite WHERE rid = ? AND uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
//            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int countRoute(int rid) {
        String sql = "SELECT count(*) FROM tab_favorite WHERE rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void addFavorite(int rid,int uid) {
        String sql = "INSERT INTO tab_favorite VALUES (?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }

    @Override
    public List findRidsByUid(int uid,int start,int pageSize) {
        List list= null;

        try {
            String sql = "SELECT * FROM tab_favorite WHERE uid = ? limit ?,?";
            list = template.queryForList(sql,uid,start,pageSize);
        } catch (DataAccessException e) {

        }
        return list;
    }

    @Override
    public int countFavorites(int uid) {
        String sql = "SELECT count(*) FROM tab_favorite WHERE uid = ?";
        return template.queryForObject(sql,Integer.class,uid);
    }

    @Override
    public List favoriteSort() {
        String sql = "SELECT rid,count(*) c FROM tab_favorite GROUP BY rid ORDER BY c desc";
        List<Map<String, Object>> maps = template.queryForList(sql);
        return maps;
    }
}
