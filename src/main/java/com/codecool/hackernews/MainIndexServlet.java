package com.codecool.hackernews;

import com.codecool.hackernews.config.TemplateEngineUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "MainIndexServlet", urlPatterns = "/")
public class MainIndexServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(getServletContext());
        JakartaServletWebApplication application = (JakartaServletWebApplication) getServletContext().getAttribute("web_app");
        WebContext webContext = new WebContext(application.buildExchange(request, response));
        webContext.setVariable("ctxPath", request.getContextPath());




        templateEngine.process("index.html", webContext, response.getWriter());
    }
}

