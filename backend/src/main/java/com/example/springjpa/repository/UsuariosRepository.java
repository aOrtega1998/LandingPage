package com.example.springjpa.repository;


import com.example.springjpa.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    @Query(value = "SELECT u FROM Usuarios u WHERE(u.numEmail = :numEmail)")
    List<Usuarios> findUsuariosByNumEmail(@Param("numEmail")Integer numEmail);

    @Query(value = "SELECT u FROM Usuarios u WHERE(u.email = :email)")
    Usuarios findUsuariosByEmail(@Param("email")String email);
}
