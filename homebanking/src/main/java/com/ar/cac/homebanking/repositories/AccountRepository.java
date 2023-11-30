package com.ar.cac.homebanking.repositories;

import com.ar.cac.homebanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    // Metodo para buscar una cuenta en la BBDD por CBU
    Account findByCbu(String cbu);

    // Metodo para buscar una cuenta en la BBDD por Alias
    Account findByAlias(String alias);

}
