package utez.edu.mx.florever.modules.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ROptionRepository extends JpaRepository<ROption,Long> {
    Optional<ROption> findByIdAndQuestionFormCreatorEmail(Long id, String email);
}
