package utez.edu.mx.florever.modules.response;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.florever.modules.user.BeanUser;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response,Long> {
    List<Response> findByUser(BeanUser user);
}
