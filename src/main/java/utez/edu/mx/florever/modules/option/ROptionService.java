package utez.edu.mx.florever.modules.option;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.florever.modules.form.dto.OptionDTO;
import utez.edu.mx.florever.modules.question.Question;
import utez.edu.mx.florever.modules.question.QuestionRepository;
import utez.edu.mx.florever.security.jwt.JWTUtils;
import utez.edu.mx.florever.utils.APIResponse;

import java.sql.SQLException;

@Service
public class ROptionService {
    @Autowired
    private ROptionRepository rOptionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse addOption(Long questionId, OptionDTO payload, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");

            Question question = questionRepository.findByIdAndFormCreatorEmail(questionId, email).orElse(null);
            if (question == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Pregunta no encontrada");
            }

            ROption option = new ROption();
            option.setValue(payload.getValue());
            option.setType(payload.getType());
            option.setQuestion(question);
            rOptionRepository.save(option);

            return new APIResponse("Opción agregada correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al agregar la opción");
        }
    }

    @Transactional(readOnly = true)
    public APIResponse getOption(Long id, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            ROption option = rOptionRepository.findByIdAndQuestionFormCreatorEmail(id, email).orElse(null);
            if (option == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Opción no encontrada");
            }

            OptionDTO dto = new OptionDTO();
            dto.setValue(option.getValue());
            dto.setType(option.getType());

            return new APIResponse("Opción encontrada", HttpStatus.OK, false, dto);
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al obtener la opción");
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse updateOption(Long id, OptionDTO payload, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            ROption option = rOptionRepository.findByIdAndQuestionFormCreatorEmail(id, email).orElse(null);
            if (option == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Opción no encontrada");
            }

            option.setValue(payload.getValue());
            option.setType(payload.getType());
            rOptionRepository.save(option);

            return new APIResponse("Opción actualizada correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al actualizar la opción");
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse deleteOption(Long id, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            ROption option = rOptionRepository.findByIdAndQuestionFormCreatorEmail(id, email).orElse(null);
            if (option == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Opción no encontrada");
            }

            rOptionRepository.delete(option);

            return new APIResponse("Opción eliminada correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al eliminar la opción");
        }
    }
}
