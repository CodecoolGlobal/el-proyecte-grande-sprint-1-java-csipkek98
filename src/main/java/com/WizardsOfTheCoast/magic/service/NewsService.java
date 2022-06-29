package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.NewsRepository;
import com.WizardsOfTheCoast.magic.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsEntity> getAllNews(){
        return newsRepository.findAll();
    }

    public void addNews(NewsEntity newsEntity){
        newsRepository.save(newsEntity);
    }
}
