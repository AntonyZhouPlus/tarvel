//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.domain.ResultInfo;
//import cn.itcast.travel.domain.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
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
// * @date 2018/7/27
// */
//@WebServlet("/showNameServlet")
//public class ShowNameServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User login_user = (User) request.getSession().getAttribute("login_user");
//        String msg = "";
//        ResultInfo resultInfo = new ResultInfo();
//
//
//        if (login_user != null) {
//            String name = login_user.getName();
//            msg = "欢迎回来！" + name + "。";
//            resultInfo.setFlag(true);
//
//        } else {
//            msg = "您尚未登陆";
//            resultInfo.setFlag(false);
//        }
//
//
//        resultInfo.setData(msg);
//        ObjectMapper om = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        om.writeValue(response.getOutputStream(),resultInfo);
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//}
