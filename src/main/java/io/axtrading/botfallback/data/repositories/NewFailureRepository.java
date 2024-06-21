package io.axtrading.botfallback.data.repositories;

import io.axtrading.botfallback.data.dto.Failure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewFailureRepository extends JpaRepository<Failure, Long> {

}
