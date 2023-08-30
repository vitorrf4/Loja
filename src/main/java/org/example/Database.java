package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
    EntityManager em = emf.createEntityManager();

    public boolean persistirProduto(Produto produto) {
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
    public boolean movimentarProduto(MovimentoProduto movimento) {
        try {

        } catch (Exception e) {

        }
        return true;
    }
}
