package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package ${PACKAGE_NAME}
 * @date 2018/7/28
 */
@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {

    FavoriteService favoriteService = new FavoriteServiceImpl();
    RouteService routeService = new RouteServiceImpl();

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ridString = request.getParameter("rid");
        int rid = 0;
        if (ridString != null && ridString.length() > 0) {
            rid = Integer.parseInt(ridString);
        }
        User login_user = (User) request.getSession().getAttribute("login_user");
        if (login_user == null) {
            writeValue(false,response);
            return;
        }

        Boolean flag = favoriteService.isFavorite(rid, login_user.getUid());

        writeValue(flag,response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ridString = request.getParameter("rid");
        int rid = Integer.parseInt(ridString);
        User login_user = (User) request.getSession().getAttribute("login_user");
        if (login_user == null) {
            return;
        }
        favoriteService.addFavorite(rid,login_user.getUid());

    }

    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User login_user = (User) request.getSession().getAttribute("login_user");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        int currentPage_int = 1;
        if (currentPage != null && currentPage.length() > 0) {
            currentPage_int = Integer.parseInt(currentPage);
        }

        int pageSize_int = 4;
        if (pageSize != null && pageSize.length() > 0) {
            pageSize_int = Integer.parseInt(pageSize);
        }

        int uid = 0;
        if (login_user != null) {
            uid = login_user.getUid();
        }

        PageBean pb = favoriteService.findRidsByUid(uid,currentPage_int,pageSize_int);
        writeValue(pb,response);
    }

    public void rank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String routeName = request.getParameter("routeName");
        String minMoneyS = request.getParameter("minMoney");
        String maxMoneyS = request.getParameter("maxMoney");

        if (routeName == null) {
            routeName = "";
        }

        int minMoney = 0;
        if (minMoneyS != null && minMoneyS.length() > 0) {
            minMoney = Integer.parseInt(minMoneyS);
        }

        int maxMoney = Integer.MAX_VALUE;
        if (maxMoneyS != null && maxMoneyS.length() > 0) {
            maxMoney = Integer.parseInt(maxMoneyS);
        }

        List<Route> routes = favoriteService.favoriteRoutes(routeName,minMoney,maxMoney);
        writeValue(routes,response);
    }

}
