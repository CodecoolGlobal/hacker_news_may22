package com.codecool.hackernews.news;

import com.codecool.hackernews.config.TemplateEngineUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "ApiDemoServlet", urlPatterns = "/demo")
public class ApiDemoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(getServletContext());
        JakartaServletWebApplication application = (JakartaServletWebApplication) getServletContext().getAttribute("web_app");
        WebContext webContext = new WebContext(application.buildExchange(request, response));
//        webContext.setVariable("name", "newsApi");
        webContext.setVariables(Map.of("name", "newsApi", "title", "May 2022 Api"));
        List<String> urls = Arrays.stream(NewsTypes.values())
                .map(newsEnum -> String.format("http://localhost:8080/hackernews_war_exploded/api?category=%s&page-number=1", newsEnum.getNewsType()))
                .collect(Collectors.toList());
        webContext.setVariable("allUrls", urls);
        templateEngine.process("api_index.html", webContext, response.getWriter());
    }
}
