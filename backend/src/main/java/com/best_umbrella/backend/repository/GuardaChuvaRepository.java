package com.best_umbrella.backend.repository;

<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

>>>>>>> the-app-with-all-the-screens
import com.best_umbrella.backend.model.GuardaChuva;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface GuardaChuvaRepository extends JpaRepository<GuardaChuva, Long> {
=======
@Repository
public interface GuardaChuvaRepository extends JpaRepository<GuardaChuva, Integer> {
>>>>>>> the-app-with-all-the-screens
    GuardaChuva findByCodigoQr(String codigoQr);
}
