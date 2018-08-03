package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service.impl
 * @date 2018/8/1
 */
public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao favoriteDao = new FavoriteDaoImpl();
    RouteDao routeDao = new RouteDaoImpl();
    UserDao userDao = new UserDaoImpl();

    @Override
    public Boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.findFavoriteByRidAndUid(rid,uid);
        return favorite != null;
    }

    @Override
    public void addFavorite(int rid,int uid) {
        favoriteDao.addFavorite(rid,uid);
    }

    @Override
    public PageBean<Favorite> findRidsByUid(int uid,int currentPage,int pageSize) {
        PageBean<Favorite> pb = new PageBean<>();
        // 获取pb的list
        int start = (currentPage - 1) * pageSize;
        List<Map> maps = favoriteDao.findRidsByUid(uid,start,pageSize);
        List<Favorite> favorites = new ArrayList<Favorite>();

        User user = userDao.findByUid(uid);
        if (maps != null) {
            for (Map map : maps) {
                Favorite favorite = new Favorite();
                Route route = routeDao.findByRid((Integer) map.get("rid"));
                favorite.setRoute(route);
                favorite.setUser(user);
                favorites.add(favorite);
            }
        }
        pb.setList(favorites);

        // 设置currentPage&pageSize
        pb.setPageSize(pageSize);
        pb.setCurrentPage(currentPage);

        // 设置totalCount
        int totalCount = favoriteDao.countFavorites(uid);
        pb.setTotalCount(totalCount);

        // 设置

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public List<Route> favoriteRoutes(String routeName,int minMoney,int maxMoney) {
        List<Map> list = favoriteDao.favoriteSort();
        List<Route> routes = new ArrayList<Route>();
        for (Map map : list) {
            Integer rid = (Integer) map.get("rid");
            int count = favoriteDao.countRoute(rid);
            Route route = routeDao.findByRid(rid);
            route.setCount(count);
            if (route.getRname().contains(routeName) && route.getPrice() > minMoney && route.getPrice() < maxMoney){
                routes.add(route);
            }
        }
        return routes;
    }
}
