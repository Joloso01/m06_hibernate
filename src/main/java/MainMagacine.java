import Controller.FileAccessor;
import Model.Article;
import Model.Revista;
import View.Menu;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMagacine {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        ArrayList<Revista> revistes = new ArrayList();
        FileAccessor fa;

        Menu menu = new Menu();
        int opcio;

        opcio = menu.menuPral();

        switch (opcio) {

            case 1:

                System.out.println("1!!");
                fa=new FileAccessor();
                try {
                    fa.readAutorsFile("src/main/java/autors.txt");
                    System.out.println();
                    System.out.println("Autors");
                    fa.printAutors();
                    fa.readMagazinesFile("src/main/java/revistes.txt");
                    System.out.println();
                    System.out.println("Revistas");
                    fa.printRevistes();
                    System.out.println();
                    System.out.println("Articles");
                    revistes=fa.readArticlesFile("src/main/java/articles.txt");
                    mostraRevistes(revistes);
                } catch (NumberFormatException | IOException e) {

                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("1.Seleccionar revista.");
                System.out.println("2.Seleccionar article");
                int op = sc.nextInt();
                switch (op){
                    case 1:
                        break;

                    case 2:
                        break;
                }
                break;

            default:
                System.out.println("Adeu!!");
                System.exit(1);
                break;

        }

    }
    public static void mostraRevistes(ArrayList<Revista> revistes){
        for (int i = 0; i < revistes.size(); i++) {

            System.out.println(revistes.get(i).toString());
            for (int j = 0; j < revistes.get(i).getArticles().size(); j++) {
                System.out.println("\t"+ revistes.get(i).getArticle(j).toString());
                System.out.println("\t\t"+revistes.get(i).getArticle(j).getAutor().toString());
            }

        }
    }

    public static Revista seleccionaRevista(ArrayList<Revista> revistes){
        //TODO

        return null;

    }
    public static Article seleccionaArticle(ArrayList<Revista> revista){
        //TODO

        return null;

    }
}
