package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao.impl
 * @date 2018/7/28
 */
public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findCounts(int cid,String rname) {
        String sql = "SELECT count(*) FROM tab_route WHERE 1 = 1";
        List prams = new ArrayList();
        if (cid != 0) {
            sql += " and cid = ? ";
            prams.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sql += " and rname like ?";
            prams.add("%"+rname+"%");
        }
        Integer count = template.queryForObject(sql,Integer.class, prams.toArray());
        return count;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        String sql = "SELECT * FROM tab_route WHERE 1 = 1";
        List prams = new ArrayList();
        if (cid != 0) {
            sql += " and cid = ? ";
            prams.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sql += " and rname like ?";
            prams.add("%"+rname+"%");
        }
        sql += " limit ?,?";
        prams.add(start);
        prams.add(pageSize);
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), prams.toArray());
        return list;
    }

    @Override
    public Route findByRid(int rid) {

        String sql = "SELECT * FROM tab_route WHERE rid = ?;";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    @Override
    public List<RouteImg> findImgsByRid(int rid) {

        String sql = " SELECT * FROM tab_route_img WHERE rid = ?;";
        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }

    @Override
    public Seller findSellerBySid(int sid) {

        String sql = "SELECT * FROM tab_seller WHERE sid = ?;";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }
}
