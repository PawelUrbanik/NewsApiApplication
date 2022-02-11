package pl.urbanik.pawel.newsapiapplication.service;

import org.springframework.stereotype.Service;
import pl.urbanik.pawel.newsapiapplication.model.Article;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class SaveData {



    public boolean saveDataToFile(Scanner sc, List<Article> articles){
            String filename = "";
            System.out.println("Podaj nazwę pliku: ");
            filename = sc.next();

            if (filename.length()==0) {
                System.out.println("*"+System.lineSeparator()+"Nie podano nazwy pliku."+System.lineSeparator()+"*");
                return false;
            }
            try(FileWriter fileWriter = new FileWriter(filename+".txt")) {
                for (Article article : articles) {
                    fileWriter.write(article.getTitle() + ":" + article.getDescription() + ":" + article.getAuthor() + System.lineSeparator());
                }
                } catch (IOException e) {
//                e.printStackTrace();
                return false;
            }
            return true;
        }
}
