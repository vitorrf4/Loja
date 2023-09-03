package org.loja.repos;

import org.loja.classes.MovimentoProduto;
import org.loja.classes.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;

public class ProdutoRepo {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
    EntityManager em = emf.createEntityManager();

    public void persistirProduto(Produto produto) {
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void persistirMovimento(MovimentoProduto movimento) {
        try {
            em.getTransaction().begin();
            em.persist(movimento);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Produto consultarProduto(int id) {
        return em.find(Produto.class, id);
    }

    public List consultarTodosMovimentos() {
        return em.createQuery("from MovimentoProduto").getResultList();
    }
}
