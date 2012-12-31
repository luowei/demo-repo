package com.book.web.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.book.bean.BrwList;
import com.book.bean.BrwCart;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent hse) {
        System.out.println("SessionListener加载。。。。。。");
        HttpSession session=hse.getSession();
        BrwCart brwCart=new BrwCart();
        session.setAttribute("brwCart", brwCart);

    }

    public void sessionDestroyed(HttpSessionEvent hse) {
        HttpSession session=hse.getSession();
        session.removeAttribute("brwCart");
    }


}
