package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service.impl
 * @date 2018/7/28
 */
public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
    FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> findByPage(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pb = new PageBean<Route>();

        pb.setCurrentPage(currentPage);

        pb.setPageSize(pageSize);

        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);

        int totalCount = routeDao.findCounts(cid, rname);
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findRoute(int rid) {

        // 这一步查询到到route不包含RouteImg Seller Category
        Route route = routeDao.findByRid(rid);
        // 获取routeImgList并设置到route
        List<RouteImg> routeImgList = routeDao.findImgsByRid(rid);
        route.setRouteImgList(routeImgList);
        // 通过route对象中到sid 获取seller并设置到route
        int sid = route.getSid();
        Seller seller = routeDao.findSellerBySid(sid);
        route.setSeller(seller);
        // 获取并设置route对象的count属性
        int count = favoriteDao.countRoute(rid);
        route.setCount(count);
        return route;
    }
}
