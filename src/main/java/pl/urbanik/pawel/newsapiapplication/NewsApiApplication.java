package pl.urbanik.pawel.newsapiapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.urbanik.pawel.newsapiapplication.model.Article;
import pl.urbanik.pawel.newsapiapplication.service.ApiService;
import pl.urbanik.pawel.newsapiapplication.service.SaveData;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class NewsApiApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(NewsApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        {

            ApiService apiService = new ApiService();
            SaveData saveData = new SaveData();
            List<Article> newsList = null;
            try(Scanner sc = new Scanner(System.in)) {
                boolean exit = false;

                do {
                    System.out.println("Wybierz opcję:");
                    System.out.println("1. Pobierz dane");
                    System.out.println("2. Zapisz dane");
                    System.out.println("3. Koniec programu");

                    int num = sc.nextInt();
                    switch (num) {
                        case 1:
                            newsList=apiService.getDataFromApi(sc);
                            if (newsList.size()>0)
                            {System.out.println("*"+System.lineSeparator()+"Pobrano artykuły"+System.lineSeparator()+"*");
                            } else
                            {
                                System.out.println("*"+System.lineSeparator()+"Błąd przy pobieraniu danych!"+System.lineSeparator()+"*");
                            }
                            break;
                        case 2:
                            if (newsList!= null && newsList.size()>0) {
                                if (saveData.saveDataToFile(sc, newsList)){
                                    System.out.println("*"+System.lineSeparator()+"Dane zostały zapisane do pliku."+System.lineSeparator()+"*");
                                }else {
                                    System.out.println("*"+System.lineSeparator()+"Wystąpił błąd przy zapisie danych."+System.lineSeparator()+"*");
                                }
                            }else {
                                System.out.println("*"+System.lineSeparator()+"Brak danych do zapisu."+System.lineSeparator()+"*");
                            }
                            break;
                        case 3:
                            exit = true;
                            break;
                        default:
                            System.out.println("*"+System.lineSeparator()+"Podano nieprawidłową opcję"+System.lineSeparator()+"*");
                            break;
                    }
                } while (!exit);
            }catch (InputMismatchException e)
            {
                System.err.println("*"+System.lineSeparator()+"Nieprawidłowy znak. Program kończy działanie"+System.lineSeparator()+"*");
            }

        }
    }
}
