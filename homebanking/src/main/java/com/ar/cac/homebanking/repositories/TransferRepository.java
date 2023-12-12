package com.ar.cac.homebanking.repositories;

import com.ar.cac.homebanking.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    // Metodo para buscar una transferencia en la BBDD por cuenta de origen
    List<Transfer> findByOriginAccount(Long originAccount);

    // Metodo para buscar una transferencia en la BBDD por cuenta de destino
    List<Transfer> findByTargetAccount(Long targetAccount);
}
