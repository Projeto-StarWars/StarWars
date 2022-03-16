package br.com.starwars.repository;
import br.com.starwars.entity.LocalizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends JpaRepository<LocalizationEntity,Long> {
}
