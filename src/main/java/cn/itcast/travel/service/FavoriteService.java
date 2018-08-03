package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service
 * @date 2018/8/1
 */
public interface FavoriteService {

    Boolean isFavorite(int rid, int uid);

    void addFavorite(int rid,int uid);

    PageBean<Favorite> findRidsByUid(int uid,int currentPage,int pageSize);

    List<Route> favoriteRoutes(String routeName,int minMoney,int maxMoney);
}
