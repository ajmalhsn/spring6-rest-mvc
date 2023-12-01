package home.springframework.practice.spring6restmvc.repositories;

import home.springframework.practice.spring6restmvc.entities.Biryani;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BiryaniRepository extends JpaRepository<Biryani, UUID> {
}
