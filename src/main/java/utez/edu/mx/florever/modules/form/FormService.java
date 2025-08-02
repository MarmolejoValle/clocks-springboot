package utez.edu.mx.florever.modules.form;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.florever.modules.user.BeanUser;
import utez.edu.mx.florever.modules.user.UserService;
import utez.edu.mx.florever.security.jwt.JWTUtils;
import utez.edu.mx.florever.utils.APIResponse;

import java.sql.SQLException;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtils jwtUtils;

    @Transactional(rollbackFor = {Exception.class , SQLException.class})
    public APIResponse save(Form form , HttpServletRequest req) {
        try{
            BeanUser beanUser = userService.getUserByMail(jwtUtils.resolveClaims(req, "sub"));
            form.setCreator(beanUser);
            formRepository.save(form);
            return new APIResponse("Se a guardado el formulario", HttpStatus.OK , false , "");
        }
        catch (Exception e){
            return new APIResponse(HttpStatus.BAD_REQUEST , true, "Error al crear el formulario");
        }
    }
}
