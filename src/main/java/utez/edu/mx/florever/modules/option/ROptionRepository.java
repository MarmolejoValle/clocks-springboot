package utez.edu.mx.florever.modules.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ROptionRepository extends JpaRepository<ROption,Long> {

}
