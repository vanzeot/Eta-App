package com.vanz.eta.repository;

import com.vanz.eta.entity.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {

    Optional<Confirmation> findFirstByOrderByIdDesc();

    Optional<Confirmation> findByNumber(@RequestParam("number") String number);

}
