package utez.edu.mx.florever.modules.question;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.florever.modules.form.Form;
import utez.edu.mx.florever.modules.form.FormRepository;
import utez.edu.mx.florever.modules.form.dto.OptionDTO;
import utez.edu.mx.florever.modules.form.dto.QuestionDTO;
import utez.edu.mx.florever.modules.option.ROption;
import utez.edu.mx.florever.modules.option.ROptionRepository;
import utez.edu.mx.florever.security.jwt.JWTUtils;
import utez.edu.mx.florever.utils.APIResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private ROptionRepository optionRepository;
    @Autowired
    private JWTUtils jwtUtils;

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse addQuestion(Long formId, QuestionDTO payload, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");

            // Validamos que el formulario exista y sea del usuario
            Form form = formRepository.findByIdAndCreatorEmail(formId, email).orElse(null);
            if (form == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Formulario no encontrado");
            }

            // Creamos la pregunta
            Question question = new Question();
            question.setText(payload.getText());
            question.setForm(form);
            Question savedQuestion = questionRepository.save(question);

            // Si hay opciones en el payload, las guardamos
            if (payload.getOptions() != null && !payload.getOptions().isEmpty()) {
                for (OptionDTO optionDTO : payload.getOptions()) {
                    ROption option = new ROption();
                    option.setValue(optionDTO.getValue());
                    option.setType(optionDTO.getType());
                    option.setQuestion(savedQuestion);
                    optionRepository.save(option);
                }
            }

            return new APIResponse("Pregunta agregada correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al agregar la pregunta");
        }
    }

    @Transactional(readOnly = true)
    public APIResponse getQuestion(Long id, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            Question question = questionRepository.findByIdAndFormCreatorEmail(id, email).orElse(null);
            if (question == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Pregunta no encontrada");
            }

            QuestionDTO dto = new QuestionDTO();
            dto.setText(question.getText());

            List<OptionDTO> optionDTOs = new ArrayList<>();
            for (ROption option : question.getOptions()) {
                OptionDTO optionDTO = new OptionDTO();
                optionDTO.setValue(option.getValue());
                optionDTO.setType(option.getType());
                optionDTOs.add(optionDTO);
            }
            dto.setOptions(optionDTOs);

            return new APIResponse("Pregunta encontrada", HttpStatus.OK, false, dto);
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al obtener la pregunta");
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse updateQuestion(Long id, QuestionDTO payload, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            Question question = questionRepository.findByIdAndFormCreatorEmail(id, email).orElse(null);
            if (question == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Pregunta no encontrada");
            }

            question.setText(payload.getText());
            questionRepository.save(question);

            return new APIResponse("Pregunta actualizada correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al actualizar la pregunta");
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse deleteQuestion(Long id, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            Question question = questionRepository.findByIdAndFormCreatorEmail(id, email).orElse(null);
            if (question == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Pregunta no encontrada");
            }

            optionRepository.deleteAll(question.getOptions());
            questionRepository.delete(question);

            return new APIResponse("Pregunta eliminada correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al eliminar la pregunta");
        }
    }
}

