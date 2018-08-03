package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
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
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();

    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");


        int cid_int = 0;
        if (cid != null && cid.length() > 0) {
            cid_int = Integer.parseInt(cid);
        }

        int currentPage_int = 1;
        if (currentPage != null && currentPage.length() > 0) {
            currentPage_int = Integer.parseInt(currentPage);
        }

        int pageSize_int = 5;
        if (pageSize != null && pageSize.length() > 0) {
            pageSize_int = Integer.parseInt(pageSize);
        }

        PageBean<Route> pb = routeService.findByPage(cid_int, currentPage_int, pageSize_int,rname);
        writeValue(pb,response);
    }


    public void routeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        if (rid == null || rid.length() == 0) {
            rid = "0";
            System.out.println("找不到rid");
        }
        Route route = routeService.findRoute(Integer.parseInt(rid));
        writeValue(route,response);

    }
}
