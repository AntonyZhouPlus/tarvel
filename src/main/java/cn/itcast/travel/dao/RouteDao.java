package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao.impl
 * @date 2018/7/28
 */
public interface RouteDao {
    int findCounts(int cid, String rname);

    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    Route findByRid(int rid);

    List<RouteImg> findImgsByRid(int rid);

    Seller findSellerBySid(int sid);
}
