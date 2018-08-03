package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package ${PACKAGE_NAME}
 * @date 2018/7/28
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        // 这步防止验证码多次使用,一旦验证码成功验证后并提交就销毁本次会话中的验证码
        request.getSession().removeAttribute("CHECKCODE_SERVER");

        // 获取参数并封装
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }



        UserService userService = new UserServiceImpl();
        User login_user = userService.login(user);

        ResultInfo resultInfo = new ResultInfo();
        if (login_user == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名密码错误,登录失败");
        } else {


            if ("Y".equals(login_user.getStatus())) {
                session.setAttribute("login_user", login_user);
                resultInfo.setFlag(true);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账户未激活,登录失败");
            }


        }

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        om.writeValue(response.getOutputStream(),resultInfo);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 这步防止验证码多次使用,一旦验证码成功验证后并提交就销毁本次会话中的验证码
        request.getSession().removeAttribute("CHECKCODE_SERVER");

        User user = new User();

        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service = new UserServiceImpl();
        boolean flag = service.register(user);
        ResultInfo ri = new ResultInfo();

        if (flag) {
            ri.setFlag(true);
        } else {
            ri.setFlag(false);
            ri.setErrorMsg("注册失败,用户名已存在");
        }

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        om.writeValue(response.getOutputStream(),ri);
    }

    public void showName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User login_user = (User) request.getSession().getAttribute("login_user");
        String msg = "";
        ResultInfo resultInfo = new ResultInfo();


        if (login_user != null) {
            String name = login_user.getName();
            msg = "欢迎！" + name ;
            resultInfo.setFlag(true);

        } else {
            msg = "您尚未登陆";
            resultInfo.setFlag(false);
        }


        resultInfo.setData(msg);
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        om.writeValue(response.getOutputStream(),resultInfo);

    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/index.html");
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String username = request.getParameter("username");


        UserService service = new UserServiceImpl();
        boolean flag = service.active(code,username);

        response.setContentType("text/html;charset=utf-8");

        if (flag) {
            response.getWriter().write("激活成功,马上跳转，请骚等");
            response.setHeader("refresh", "2;URL=http://localhost:8080/travel");
        } else {
            response.getWriter().write("激活失败，请充值");
        }
    }


}
