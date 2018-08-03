//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.service.UserService;
//import cn.itcast.travel.service.impl.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author zhouhang
// * @project_name tarvel
// * @package ${PACKAGE_NAME}
// * @date 2018/7/25
// */
//@WebServlet("/activeServlet")
//public class ActiveServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String code = request.getParameter("code");
//        String username = request.getParameter("username");
//
//
//        UserService service = new UserServiceImpl();
//        boolean flag = service.active(code,username);
//
//        response.setContentType("text/html;charset=utf-8");
//
//        if (flag) {
//            response.getWriter().write("激活成功,马上跳转，请骚等");
//            response.setHeader("refresh", "2;URL=http://localhost:8080/travel");
//        } else {
//            response.getWriter().write("激活失败，请充值");
//        }
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//}
