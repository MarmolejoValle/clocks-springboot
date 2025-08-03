package utez.edu.mx.florever.modules.form;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.florever.modules.form.dto.FormDTO;
import utez.edu.mx.florever.utils.APIResponse;

@RestController
@RequestMapping("/api/form")
@Tag(name = "Controlador de Formularios", description = "Operaciones relacionadas con Formularios")
@SecurityRequirement(name = "bearerAuth")
public class FormController {
    @Autowired
    FormService formService;
    @PostMapping
    public ResponseEntity save(@RequestBody FormDTO payload , HttpServletRequest req){
        APIResponse response = formService.save(payload, req);
        return new ResponseEntity<>(response, response.getStatus());

    }

    @GetMapping
    public ResponseEntity listForms(HttpServletRequest req) {
        APIResponse response = formService.listForms(req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity getForm(@PathVariable Long id, HttpServletRequest req) {
        APIResponse response = formService.getForm(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody FormDTO payload, HttpServletRequest req) {
        APIResponse response = formService.update(id, payload, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id, HttpServletRequest req) {
        APIResponse response = formService.delete(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
