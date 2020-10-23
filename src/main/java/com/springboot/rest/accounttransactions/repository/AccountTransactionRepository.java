package com.springboot.rest.accounttransactions.repository;

import com.springboot.rest.accounttransactions.entity.AccountTransaction;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

  @Query(value = "select * from account_transaction where account_number = :accountNumber", nativeQuery = true)
  List<AccountTransaction> findByAccountNumber(@NonNull final @Param("accountNumber") String accountNumber);

}
