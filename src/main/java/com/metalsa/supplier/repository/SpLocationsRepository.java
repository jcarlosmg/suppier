package com.metalsa.supplier.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metalsa.supplier.entity.SpTblCatLocalizacion;

@Repository
public interface SpLocationsRepository extends JpaRepository<SpTblCatLocalizacion, BigDecimal>{
    
}
