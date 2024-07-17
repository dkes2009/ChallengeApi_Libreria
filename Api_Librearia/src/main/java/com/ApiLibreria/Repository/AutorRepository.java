package com.ApiLibreria.Repository;

import com.ApiLibreria.Entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<AutorEntity, Integer> {

    List<AutorEntity> findAllByOrderByIdDesc();

}
