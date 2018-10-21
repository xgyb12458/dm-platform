//package com.damon.oauth.starter.filter;
//
//import com.damon.oauth.shared.enums.KickOutStrategy;
//import com.damon.oauth.enums.KickOutStrategy;
//import com.damon.oauth.domain.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Setter;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheManager;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.mgt.DefaultSessionKey;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.AccessControlFilter;
//import org.apache.shiro.web.util.WebUtils;
//import org.springframework.util.ObjectUtils;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.PrintWriter;
//import java.io.Serializable;
//import java.util.*;
//
///**
// * 1.读取当前登录用户名，获取缓存中的sessionId队列
// * 2.判断队列的长度，大于最大登录限制的时候，按踢出规则将之前sessionId中的session域中存入kickout：true，并更新队列缓存
// * 3.判断当前登录的session域中的kickout如果为true，先将其做退出登录处理，然后再重定向到踢出登录提示页面
// */
//public class KickOutSessionFilter extends AccessControlFilter {
//
//    @Setter
//    private String kickOutUrl;
//
//    /**同一个帐号最大会话数 默认1*/
//    @Setter
//    private int maxSession = 1;
//
//    @Setter
//    private SessionManager sessionManager;
//
//    @Setter
//    private KickOutStrategy strategy = KickOutStrategy.FIFO;
//
//    private Cache<String, Deque<Serializable>> cachedUsers;
//
//    private final static String KICK_OUT = "kickOut";
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    /**设置Cache的key的前缀*/
//    public void setCacheManager(CacheManager cacheManager) {
//        this.cachedUsers = cacheManager.getCache("shiro_redis_cache");
//    }
//
//
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        return false;
//    }
//
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        Subject subject = getSubject(request, response);
//        if (!subject.isAuthenticated() && !subject.isRemembered()) {
//            return true;
//        }
//
//        User user = (User) subject.getPrincipal();
//        Session session = subject.getSession();
//
//        //如果此用户没有session队列，即还未登录过，则new一个空队列
//        Deque<Serializable> loginDeque = Optional.ofNullable(cachedUsers.get(user.getName())).orElse(new LinkedList<>());
//
//        //如果队列里没有此sessionId，且用户没有被踢出，则放入队列
//        if(ObjectUtils.isEmpty(session.getAttribute(KICK_OUT))
//                && !loginDeque.contains(session.getId())) {
//            loginDeque.push(session.getId());
//            cachedUsers.put(user.getName(), loginDeque);
//        }
//
//        //如果队列里的sessionId数超出最大会话数，开始踢人
//        while(loginDeque.size() > maxSession) {
//            Serializable kickOutSessionId = strategy.equals(KickOutStrategy.FIFO) ? loginDeque.removeFirst() : loginDeque.removeLast();
//            cachedUsers.put(user.getName(), loginDeque);
//
//            Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickOutSessionId));
//            Optional.ofNullable(kickoutSession).ifPresent(kickOut -> {
//                //设置会话的kickout属性表示踢出了
//                kickOut.setAttribute(KICK_OUT, true);
//            });
//        }
//
//        //如果被踢出了，直接退出，重定向到踢出后的地址
//        if (!ObjectUtils.isEmpty(session.getAttribute(KICK_OUT))
//                && (Boolean)session.getAttribute(KICK_OUT)) {
//            subject.logout();
//            saveRequest(request);
//
//            //判断是不是Ajax请求
//            if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
//                Map<String, String> resultMap = new HashMap<>(2);
//                resultMap.put("user_status", "300");
//                resultMap.put("message", "您已经在其他地方登录，请重新登录！");
//
//                outputJSON(response, resultMap);
//            } else {
//                WebUtils.issueRedirect(request, response, kickOutUrl);
//            }
//            return false;
//        }
//        return true;
//    }
//
//    private void outputJSON(ServletResponse response, Map<String, String> resultMap) {
//        try {
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter outWriter = response.getWriter();
//            outWriter.println(objectMapper.writeValueAsString(resultMap));
//            outWriter.flush();
//            outWriter.close();
//        } catch (Exception e) {
//            System.err.println("KickOutSessionFilter.class 输出JSON异常。");
//        }
//
//    }
//}
