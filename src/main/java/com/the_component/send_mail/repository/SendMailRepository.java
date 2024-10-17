package com.the_component.send_mail.repository;

import com.the_component.send_mail.model.StorageCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendMailRepository extends JpaRepository<StorageCode, Long> {
    boolean existsByEmail(String email);

    StorageCode getByEmail(String email);
}
