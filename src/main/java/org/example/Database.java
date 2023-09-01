package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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
            throw e;
        }
        return true;
    }

    public boolean movimentarProduto(MovimentoProduto movimento) {
        try {
            em.getTransaction().begin();
            em.persist(movimento);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
        return true;
    }

    public Produto consultarProduto(int id) {
        return em.find(Produto.class, id);
    }

    public List consultarTodosMovimentos() {
        return em.createQuery("from MovimentoProduto").getResultList();
    }
}
