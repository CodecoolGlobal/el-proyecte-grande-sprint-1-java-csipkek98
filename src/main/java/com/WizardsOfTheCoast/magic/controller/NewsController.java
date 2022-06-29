package com.WizardsOfTheCoast.magic.controller;


import com.WizardsOfTheCoast.magic.Data_sample.NewsCreator;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.NewsEntity;
import com.WizardsOfTheCoast.magic.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(value = "/news")
    public List<NewsEntity> Greetings() throws ParseException {
        List<NewsEntity> news = NewsCreator.createNews();
        for (NewsEntity newsEntity : news) {
            newsService.addNews(newsEntity);
        }
        return newsService.getAllNews();
    }

}
