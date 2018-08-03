package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package ${PACKAGE_NAME}
 * @date 2018/7/25
 */

/**
 * 用于验证码异步校验
 */
@WebServlet("/cCServlet")
public class CCServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String checkcode = request.getParameter("checkcode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        session.removeAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkcode)) {
            // 验证码错误
            info.setFlag(false);
        } else {
            // 验证码正确
            info.setFlag(true);
        }
        // 将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        // 设置contenttype以及字符集 并写回前端(异步请求ajax)
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
