package dominio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static java.util.Objects.isNull;

public class DAO<E> {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private Class<E> entidade;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("HibernateAula");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public DAO(Class<E> entidade) {
        this.entidade = entidade;
        em = emf.createEntityManager();
    }

    public DAO<E> abrir() {
        em.getTransaction().begin();
        return this;
    }

    public DAO<E> fechar() {
        em.getTransaction().commit();
        return this;
    }

    public DAO<E> create(E entidade) {
        em.persist(entidade);
        return this;
    }
    public E encontrarPeloId(Object id) {
        return em.find(entidade, id);
    }

    public DAO<E> delete(int id) {
        DAO<E> dao = new DAO<E>(entidade);
        E ninjaEncontrado = dao.encontrarPeloId(id);
        em.remove(em.contains(ninjaEncontrado) ? ninjaEncontrado : em.merge(ninjaEncontrado));
        return this;
    }

    public Pessoa atualizar(int id, String nome, String email) {
        DAO<E> dao = new DAO<E>(entidade);
        dao.abrir();

        Pessoa pessoa = (Pessoa) dao.encontrarPeloId(id);
        pessoa.setNome(nome);
        pessoa.setEmail(email);

        em.merge(pessoa);

        dao.fechar();

        return pessoa;
    }

}

