package top.cfish.sso.server.filter;

import top.cfish.sso.client.constant.AuthConst;
import top.cfish.sso.server.storage.ClientStorage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter
{
    @Override
    public void init(FilterConfig config) throws ServletException
    {
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        
        // 注销请求，放行
        if ("/logout".equals(uri))
        {
            chain.doFilter(req, res);
            return;
        }
        
        // 已经登录，放行
        if (session.getAttribute(AuthConst.IS_LOGIN) != null)
        {
            // 如果是客户端发起的登录请求，跳转回客户端，并附带token
            String clientUrl = request.getParameter(AuthConst.CLIENT_URL);
            String token = (String)session.getAttribute(AuthConst.TOKEN);
            if (clientUrl != null && !"".equals(clientUrl))
            {
                // 存储，用于注销
                ClientStorage.INSTANCE.set(token, clientUrl);
                response.sendRedirect(clientUrl + "?" + AuthConst.TOKEN + "=" + token);
                return;
            }
            if (!"/success".equals(uri))
            {
                response.sendRedirect("/success");
                return;
            }
            chain.doFilter(req, res);
            return;
        }
        // 登录请求，放行
        if ("/".equals(uri) || "/login".equals(uri))
        {
            chain.doFilter(req, res);
            return;
        }
        
        // 其他请求，拦截
        response.sendRedirect("/");
    }
    
    @Override
    public void destroy()
    {
    }
}
