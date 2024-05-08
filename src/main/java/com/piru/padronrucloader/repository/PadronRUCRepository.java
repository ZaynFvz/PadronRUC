package com.piru.padronrucloader.repository;

import com.piru.padronrucloader.model.PadronRUC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PadronRUCRepository extends JpaRepository<PadronRUC, Long> {

    List<PadronRUC> findByRucContainingIgnoreCase(String ruc);
}
