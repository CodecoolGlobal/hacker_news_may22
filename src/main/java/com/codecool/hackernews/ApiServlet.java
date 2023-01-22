package com.codecool.hackernews;

import com.codecool.hackernews.news.News;
import com.codecool.hackernews.news.NewsApiService;
import com.codecool.hackernews.news.NewsApiServiceImpl;
import com.codecool.hackernews.news.NewsTypes;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ApiServlet", urlPatterns = "/api")
public class ApiServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = null;
        Integer pageNumber = null;
        try {
            category = request.getParameter("category");
            pageNumber = Integer.valueOf(request.getParameter("page"));
        } catch (Exception e) {
            response.sendError(400);
            return;
        }
        NewsApiService newsApiService = new NewsApiServiceImpl();
        List<News> allNews = newsApiService.getNews(NewsTypes.getNewsTypeEnum(category), pageNumber);
        response.setContentType("application/json");
        response.getWriter().println(new Gson().toJson(allNews));
    }
}
