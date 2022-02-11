package pl.urbanik.pawel.newsapiapplication.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.urbanik.pawel.newsapiapplication.model.Article;
import pl.urbanik.pawel.newsapiapplication.model.NewsResponse;

import java.util.List;
import java.util.Scanner;

@Service
public class ApiService  {

    private Gson gson;

    public ApiService() {
        gson = new Gson();
    }

    public List<Article> getDataFromApi(Scanner sc) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=pl&category=business&apiKey=22638a5767cf48f4891fbc3374dcc38f", String.class);
        NewsResponse newsResponse = gson.fromJson(response, NewsResponse.class);
        return newsResponse.getArticles();
    }
}
