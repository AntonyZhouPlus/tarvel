package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package ${PACKAGE_NAME}
 * @date 2018/7/28
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求路径
        String uri = req.getRequestURI();
        // 获取方法名 /travel/trash/<<add>>   <<方法名>> 注意+1
        String methodName = uri.substring(uri.lastIndexOf('/')+1);


        // 获取方法
        try {
            // 注意只能执行子类中 方法参数为 req resp 并且修饰符为public
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 使用子类对象调用，传入容器的req，resp
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public void writeValue(Object obj,HttpServletResponse response) {
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            om.writeValue(response.getOutputStream(),obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String writeValueAsString(Object obj) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
