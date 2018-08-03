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
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;
//
///**
// * @author zhouhang
// * @project_name tarvel
// * @package ${PACKAGE_NAME}
// * @date 2018/7/25
// */
//@WebServlet("/userRegisterServlet")
//public class UserRegisterServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//
//        // 这步防止验证码多次使用,一旦验证码成功验证后并提交就销毁本次会话中的验证码
//        request.getSession().removeAttribute("CHECKCODE_SERVER");
//
//        User user = new User();
//
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        try {
//            BeanUtils.populate(user,parameterMap);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        UserService service = new UserServiceImpl();
//        boolean flag = service.register(user);
//        ResultInfo ri = new ResultInfo();
//
//        if (flag) {
//            ri.setFlag(true);
//        } else {
//            ri.setFlag(false);
//            ri.setErrorMsg("注册失败,用户名已存在");
//        }
//
//        response.setContentType("application/json;charset=utf-8");
//        ObjectMapper om = new ObjectMapper();
//        om.writeValue(response.getOutputStream(),ri);
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//}
