import Controller.FileAccessor;
import Model.Article;
import Model.Autor;
import Model.Revista;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class AutorJPAManager {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("MagazineJPA");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        AutorJPAManager MA = new AutorJPAManager();
        FileAccessor fa1;
        fa1 = new FileAccessor();
        fa1.readAutorsFile("src/main/java/autors.txt");
        System.out.println("Autors llegits des del fitxer");
        for (int i = 0; i < fa1.llistaAutors.size(); i++) {
            System.out.println(fa1.llistaAutors.get(i).toString());
            MA.addAutor(fa1.llistaAutors.get(i));
        }
        System.out.println("Autors llegits de la base de dades");
        MA.listAutors();
        MA.deleteAutor(5);
        MA.updateAutor(12, false);
        System.out
                .println("Autors llegits de la base de dades després de des actualitzacions");
        MA.listAutors();

        fa1.readMagazinesFile("src/main/java/revistes.txt");
        System.out.println("Revistes llegits des del fitxer");
        for (int i = 0; i < fa1.llistaRevistes.size(); i++) {
            System.out.println(fa1.llistaRevistes.get(i).toString());
            MA.addRevistas(fa1.llistaRevistes.get(i));
        }
        System.out.println("Articles llegits de la base de dades");
        MA.listRevista();
    }

    private void addRevistas(Revista revista) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Revista revista2 = em.find(Revista.class, revista.getId_revista());
        if (revista2 ==null){
            em.persist(revista2);
        }else {
            em.merge(revista2);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void listRevista() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Revista> result = em.createQuery("from revistes", Revista.class)
                .getResultList();
        for (Revista revista : result) {
            System.out.println(revista.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to CREATE an Autor in the database */
    public void addAutor(Autor autor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Autor autor2 = em.find(Autor.class, autor.getId_autor());
        if (autor2 ==null){
            em.persist(autor);
        }else {
            em.merge(autor);
        }
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Autors */
    public void listAutors() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Autor> result = em.createQuery("from Autor", Autor.class)
                .getResultList();
        for (Autor autor : result) {
            System.out.println(autor.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an autor */
    public void updateAutor(Integer AutorID, boolean actiu) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Autor autor = em.find(Autor.class, AutorID);
        autor.setActiu(actiu);
        em.merge(autor);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an employee from the records */
    public void deleteAutor(Integer AutorID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Autor autor = em.find(Autor.class, AutorID);
        em.remove(autor);
        em.getTransaction().commit();
        em.close();
    }

    public void addArticulos (Article article){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Article article2 = em.find(Article.class, article.getId_article());
        if (article2 ==null){
            em.persist(article);
        }else {
            em.merge(article2);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void listArticulos() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Article> result = em.createQuery("from articles", Article.class)
                .getResultList();
        for (Article article : result) {
            System.out.println(article.toString());
        }
        em.getTransaction().commit();
        em.close();

    }
}
