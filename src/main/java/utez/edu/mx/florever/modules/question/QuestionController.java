package utez.edu.mx.florever.modules.question;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.florever.modules.form.dto.QuestionDTO;
import utez.edu.mx.florever.utils.APIResponse;

@RestController
@RequestMapping("/api/question")
@Tag(name = "Controlador de Preguntas", description = "Operaciones relacionadas con Preguntas")
@SecurityRequirement(name = "bearerAuth")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/form/{formId}")
    public ResponseEntity addQuestion(@PathVariable Long formId, @RequestBody QuestionDTO payload, HttpServletRequest req) {
        APIResponse response = questionService.addQuestion(formId, payload, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestion(@PathVariable Long id, HttpServletRequest req) {
        APIResponse response = questionService.getQuestion(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO payload, HttpServletRequest req) {
        APIResponse response = questionService.updateQuestion(id, payload, req);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestion(@PathVariable Long id, HttpServletRequest req) {
        APIResponse response = questionService.deleteQuestion(id, req);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
