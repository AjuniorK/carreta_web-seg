package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public interface Clientes extends JpaRepository<Cliente, Integer > {

    //@Transactional
    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> encontrarPorNome(@Param("nome") String nome );

    @Modifying
    @Transactional
    @Query(" delete from Cliente c where c.nome =:nome ")
    void deleteByNome(@Param("nome") String nome);

    List<Cliente> findByNomeLike(String nome);

    Cliente findClienteById(Integer id);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );


//    @PersistenceContext
//    private EntityManager entityManager;
//
//    //private EntityManager entityManager;
//
//    @Transactional
//    public Cliente salvar(Cliente cliente){
//        entityManager.persist(cliente);
//        return cliente;
//    }
//
//    @Transactional
//    public Cliente atualizar(Cliente cliente){
//        entityManager.merge(cliente);
//        return cliente;
//    }
//
//    @Transactional
//    public void deletar(Cliente cliente){
//        if(!entityManager.contains(cliente)){
//            cliente = entityManager.merge(cliente);
//        }
//        entityManager.remove(cliente);
//    }
//
//    @Transactional
//    public void deletar(Integer id){
//        Cliente cliente = entityManager.find( Cliente.class, id );
//        deletar(cliente);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> buscarPorNome(String nome){
//        String jpql = " select c from Cliente c where c.nome like :nome ";
//        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
//        query.setParameter("nome", "%" + nome +"%");
//        return query.getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> obterTodos(){
//        return entityManager
//                .createQuery("from Cliente", Cliente.class)
//                .getResultList();
//    }
}
