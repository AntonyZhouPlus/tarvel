package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package cn.itcast.travel.service
 * @date 2018/7/28
 */
public interface RouteService {
    PageBean<Route> findByPage(int cid,int currentPage,int pageSize,String rname);

    Route findRoute(int rid);
}
