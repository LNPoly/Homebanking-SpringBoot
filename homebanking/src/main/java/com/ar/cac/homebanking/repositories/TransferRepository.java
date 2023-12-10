package com.ar.cac.homebanking.repositories;

import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findByOriginAccount(Long originAccount);

    List<Transfer> findByTargetAccount(Long targetAccount);
}
