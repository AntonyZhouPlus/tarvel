package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.dao
 * @date 2018/8/1
 */
public interface FavoriteDao {

    Favorite findFavoriteByRidAndUid(int rid, int uid);

    int countRoute(int rid);

    void addFavorite(int rid,int uid);

    List findRidsByUid(int uid,int start,int pageSize);

    int countFavorites(int uid);

    List favoriteSort();
}
