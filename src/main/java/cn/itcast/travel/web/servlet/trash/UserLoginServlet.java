//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.domain.ResultInfo;
//import cn.itcast.travel.domain.User;
//import cn.itcast.travel.service.UserService;
//import cn.itcast.travel.service.impl.UserServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.beanutils.BeanUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;
//
///**
// * @author zhouhang
// * @project_name tarvel
// * @package ${PACKAGE_NAME}
// * @date 2018/7/27
// */
//@WebServlet("/userLoginServlet")
//public class UserLoginServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        request.setCharacterEncoding("utf-8");
//        HttpSession session = request.getSession();
//        // 这步防止验证码多次使用,一旦验证码成功验证后并提交就销毁本次会话中的验证码
//        request.getSession().removeAttribute("CHECKCODE_SERVER");
//
//        // 获取参数并封装
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        User trash = new User();
//        try {
//            BeanUtils.populate(trash,parameterMap);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//
//
//        UserService userService = new UserServiceImpl();
//        User login_user = userService.login(trash);
//
//        ResultInfo resultInfo = new ResultInfo();
//        if (login_user == null) {
//            resultInfo.setFlag(false);
//            resultInfo.setErrorMsg("用户名密码错误,登录失败");
//        } else {
//
//
//            if ("Y".equals(login_user.getStatus())) {
//                session.setAttribute("login_user", login_user);
//                resultInfo.setFlag(true);
//            } else {
//                resultInfo.setFlag(false);
//                resultInfo.setErrorMsg("账户未激活,登录失败");
//            }
//
//
//        }
//
//        ObjectMapper om = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        om.writeValue(response.getOutputStream(),resultInfo);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//}
