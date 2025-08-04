package utez.edu.mx.florever.modules.response;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.florever.modules.option.ROption;
import utez.edu.mx.florever.utils.APIResponse;

@RestController
@RequestMapping("/api/response")
@Tag(name = "Controlador de Respuestas", description = "Operaciones relacionadas con Respuestas")
@SecurityRequirement(name = "bearerAuth")
public class ResponseController {
    @Autowired
    ResponseService responseService;
    @PostMapping("/{id}")
    public ResponseEntity save(Long id , HttpServletRequest req){
        APIResponse response = responseService.save(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }
    @GetMapping("/form/{id}")
    public ResponseEntity findAllByFormId(@PathVariable Long id, HttpServletRequest req){
        APIResponse response = responseService.findAllResponseByForm(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
