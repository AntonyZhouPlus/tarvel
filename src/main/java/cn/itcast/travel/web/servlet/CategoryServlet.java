package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author zhouhang
 * @project_name tarvel
 * @package ${PACKAGE_NAME}
 * @date 2018/7/28
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> list = categoryService.findAll();
        writeValue(list,response);
    }

}
