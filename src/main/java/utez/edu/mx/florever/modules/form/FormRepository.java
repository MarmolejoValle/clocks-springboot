package utez.edu.mx.florever.modules.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.florever.modules.form.dto.FormDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form,Long> {
    List<Form> findAllByCreatorEmail(String email);
    Optional<Form> findByIdAndCreatorEmail(Long id, String email);
}
