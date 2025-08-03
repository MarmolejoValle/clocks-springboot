package utez.edu.mx.florever.modules.option;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.florever.modules.form.dto.OptionDTO;
import utez.edu.mx.florever.modules.question.Question;
import utez.edu.mx.florever.modules.response.Response;
import utez.edu.mx.florever.utils.APIResponse;

import java.util.List;

@RestController
@RequestMapping("/api/option")
@Tag(name = "Controlador de Opciones", description = "Operaciones relacionadas con Opciones")
@SecurityRequirement(name = "bearerAuth")
public class ROptionController {

    @Autowired
    private ROptionService optionService;

    @PostMapping("/question/{questionId}")
    public ResponseEntity addOption(@PathVariable Long questionId, @RequestBody OptionDTO payload, HttpServletRequest req) {
        APIResponse response = optionService.addOption(questionId, payload, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOption(@PathVariable Long id, HttpServletRequest req) {
        APIResponse response = optionService.getOption(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOption(@PathVariable Long id, @RequestBody OptionDTO payload, HttpServletRequest req) {
        APIResponse response = optionService.updateOption(id, payload, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOption(@PathVariable Long id, HttpServletRequest req) {
        APIResponse response = optionService.deleteOption(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
