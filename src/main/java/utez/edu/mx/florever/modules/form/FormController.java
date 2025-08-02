package utez.edu.mx.florever.modules.form;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utez.edu.mx.florever.utils.APIResponse;

@RestController
@RequestMapping("/api/form")
public class FormController {
    @Autowired
    FormService formService;
    @PostMapping
    public ResponseEntity save(@RequestBody Form form , HttpServletRequest req){
        APIResponse response = formService.save(form, req);
        return new ResponseEntity<>(response, response.getStatus());

    }
}
