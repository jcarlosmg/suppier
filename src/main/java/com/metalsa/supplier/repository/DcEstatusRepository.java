package com.metalsa.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.metalsa.supplier.entity.DcEstatus;

@Repository
public interface DcEstatusRepository extends JpaRepository<DcEstatus, Integer>{
    DcEstatus findByDescEstatus(String desc);
}
