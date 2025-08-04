package utez.edu.mx.florever.modules.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import utez.edu.mx.florever.modules.form.Form;
import utez.edu.mx.florever.modules.form.FormRepository;
import utez.edu.mx.florever.modules.option.ROption;
import utez.edu.mx.florever.modules.option.ROptionRepository;
import utez.edu.mx.florever.modules.user.BeanUser;
import utez.edu.mx.florever.modules.user.UserService;
import utez.edu.mx.florever.security.jwt.JWTUtils;
import utez.edu.mx.florever.utils.APIResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    ROptionRepository optionRepository;
    @Autowired
    FormRepository formRepository;
    public APIResponse save(Long id , HttpServletRequest req){
        try {
            ROption option = optionRepository.findById(id).orElse(null);
            if (option == null) {
                return new APIResponse(HttpStatus.BAD_REQUEST, true, "No existe en el registro");
            }
            BeanUser beanUser = userService.getUserByMail(jwtUtils.resolveClaims(req, "sub"));
            if (beanUser == null) {
                return new APIResponse(HttpStatus.BAD_REQUEST, true, "No existe en el Usuario");
            }

            if (option.getResponses().stream().anyMatch(response ->  response.getUser().getId().equals(beanUser.getId())))
            {
                return new APIResponse(HttpStatus.BAD_REQUEST, true, "No se puede votar dos veces del Usuario");
            }
            if (option.getQuestion().getOptions().stream()
                    .anyMatch(op -> op.getResponses().stream()
                            .anyMatch(response -> response.getUser().getId().equals(beanUser.getId()))))
            {
              Long idResponse =   option.getQuestion().getOptions().stream()
                        .flatMap(op -> op.getResponses().stream())
                        .filter(response -> response.getUser().getId().equals(beanUser.getId()))
                        .map(Response::getId)
                        .findFirst().orElse(null);
              if (idResponse == null) {
                  return new APIResponse(HttpStatus.BAD_REQUEST, true, "No dos opciones de la misma pregunta : " + idResponse);
              }
              responseRepository.deleteById(idResponse);
            }
            Response response = new Response();
            response.setOp(option);
            response.setUser(beanUser);
            responseRepository.save(response);
            return new APIResponse("Se a guardado la opccion correctamente",HttpStatus.OK, true, "Se a guardado la opccion correctamente");
        }
        catch (Exception e){
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al cargar la respuesta");
        }
    }

    public APIResponse findAllResponseByForm(Long idForm, HttpServletRequest req){
        try {
            BeanUser beanUser = userService.getUserByMail(jwtUtils.resolveClaims(req, "sub"));
            Form form = formRepository.findById(idForm).orElse(null);
            if (form == null) {
                return new APIResponse(HttpStatus.BAD_REQUEST, true, "No existe en el formulario");
            }
            List<Response> userResponses = form.getQuestions().stream()
                    .flatMap(question -> question.getOptions().stream()) // aplanar opciones
                    .flatMap(option -> option.getResponses().stream())   // aplanar respuestas
                    .filter(response -> response.getUser().getId().equals(beanUser.getId()))
                    .collect(Collectors.toList());
            List<Long> responsesOptions = userResponses.stream()
                    .map(response -> response.getOp().getId())
                    .collect(Collectors.toList());            return new APIResponse("Lista de repuestas" , HttpStatus.OK, true, responsesOptions);
        }
        catch (Exception e){
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al buscar las respuestas");
        }
    }
}
