package com.pn.filter;

import com.alibaba.fastjson.JSON;
import com.pn.entity.Result;
import com.pn.utils.WarehouseConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录限制过滤器:
 */
public class SecurityFilter implements Filter {

    //将redis模板定义为其成员变量
    private StringRedisTemplate redisTemplate;

    //成员变量redis模板的set方法
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 过滤器拦截到请求执行的方法:
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        //转型，转成实现类
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //获取请求url接口
        String path = request.getServletPath();
        /*
          白名单请求都直接放行:
         */
        List<String> urlList = new ArrayList<>();
        urlList.add("/captcha/captchaImage");
        urlList.add("/login");
        urlList.add("/logout");
        //对上传图片的url接口/product/img-upload的请求直接放行
        urlList.add("/product/img-upload");
        //对static下的/img/upload中的静态资源图片的访问直接放行
        if(urlList.contains(path)||path.contains("/img/upload")){
            chain.doFilter(request, response);
            return;
        }

        /*
          其它请求都校验token:
         */
        //拿到前端归还的token（token都是放在请求头中）
        String clientToken = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        //校验token,校验通过请求放行
        if(StringUtils.hasText(clientToken)&&redisTemplate.hasKey(clientToken)){
            chain.doFilter(request, response);
            return;
        }
        //校验失败,向前端响应失败的Result对象转成的json串
        Result result = Result.err(Result.CODE_ERR_UNLOGINED, "请登录！");
        String jsonStr = JSON.toJSONString(result);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

}
