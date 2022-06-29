package com.WizardsOfTheCoast.magic.Data_sample;

import com.WizardsOfTheCoast.magic.entity.NewsEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsCreator {

    public static List<NewsEntity> createNews() throws ParseException {
        List<NewsEntity> news = new ArrayList<>();
        NewsEntity newsEntity = NewsEntity.builder()
                .description("Oh look, its a news")
                .publicationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01"))
                .title("Test title")
                .build();
        NewsEntity newsEntity2 = NewsEntity.builder()
                .description("Oh look, its another news")
                .publicationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-02"))
                .title("Test title2")
                .build();
        news.add(newsEntity2);
        news.add(newsEntity);
        return news;
    }

}
