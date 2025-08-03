package utez.edu.mx.florever.modules.form;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.florever.modules.form.dto.FormDTO;
import utez.edu.mx.florever.modules.form.dto.OptionDTO;
import utez.edu.mx.florever.modules.form.dto.QuestionDTO;
import utez.edu.mx.florever.modules.option.ROption;
import utez.edu.mx.florever.modules.option.ROptionRepository;
import utez.edu.mx.florever.modules.question.Question;
import utez.edu.mx.florever.modules.question.QuestionRepository;
import utez.edu.mx.florever.modules.user.BeanUser;
import utez.edu.mx.florever.modules.user.UserService;
import utez.edu.mx.florever.security.jwt.JWTUtils;
import utez.edu.mx.florever.utils.APIResponse;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private ROptionRepository optionRepository;
    @Autowired
    private QuestionRepository questionRepository;


    @Transactional(rollbackFor = {Exception.class , SQLException.class})
    public APIResponse save(FormDTO payload , HttpServletRequest req) {
        try{
            System.out.println("User : " + jwtUtils.resolveClaims(req, "sub"));

            BeanUser beanUser = userService.getUserByMail(jwtUtils.resolveClaims(req, "sub"));
            //Datos del formulario
            Form form = new Form();
            form.setTitle(payload.getTitle());
            form.setDescription(payload.getDescription());
            form.setType(payload.getType());
            form.setOpen(payload.getOpen());
            form.setClosed(payload.getClosed());
            form.setCreated(LocalDateTime.now());
            form.setCreator(beanUser);
           Form formSave = formRepository.save(form);
            //Datos de las preguntas
            for (QuestionDTO quesPayload : payload.getQuestions()) {
                Question question = new Question();
                question.setForm(formSave);
                question.setText(quesPayload.getText());
                Question questionSave =  questionRepository.save(question);

                for(OptionDTO optionPayload : quesPayload.getOptions()) {
                    ROption option = new ROption();
                    option.setType(optionPayload.getType());
                    option.setValue(optionPayload.getValue());
                    option.setQuestion(questionSave);
                    optionRepository.save(option);

                }
            }

            return new APIResponse("Se a guardado el formulario", HttpStatus.OK , false , "");
        }
        catch (Exception e){
            System.out.println(e);
            return new APIResponse(HttpStatus.BAD_REQUEST , true, "Error al crear el formulario");
        }
    }

    @Transactional(readOnly = true)
    public APIResponse listForms(HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            List<Form> forms = formRepository.findAllByCreatorEmail(email);
            List<FormDTO> formDTOs = new ArrayList<>();

            for (Form form : forms) {
                FormDTO dto = new FormDTO();
                dto.setTitle(form.getTitle());
                dto.setDescription(form.getDescription());
                dto.setType(form.getType());
                dto.setOpen(form.getOpen());
                dto.setClosed(form.getClosed());

                List<QuestionDTO> questionDTOs = new ArrayList<>();
                for (Question question : form.getQuestions()) {
                    QuestionDTO questionDTO = new QuestionDTO();
                    questionDTO.setText(question.getText());

                    List<OptionDTO> optionDTOs = new ArrayList<>();
                    for (ROption option : question.getOptions()) {
                        OptionDTO optionDTO = new OptionDTO();
                        optionDTO.setValue(option.getValue());
                        optionDTO.setType(option.getType());
                        optionDTOs.add(optionDTO);
                    }

                    questionDTO.setOptions(optionDTOs);
                    questionDTOs.add(questionDTO);
                }

                dto.setQuestions(questionDTOs);
                formDTOs.add(dto);
            }

            return new APIResponse("Formularios encontrados", HttpStatus.OK, false, formDTOs);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al listar formularios");
        }
    }

    @Transactional(readOnly = true)
    public APIResponse getForm(Long id, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            Form form = formRepository.findByIdAndCreatorEmail(id, email)
                    .orElse(null);

            if (form == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Formulario no encontrado");
            }

            FormDTO dto = new FormDTO();
            dto.setTitle(form.getTitle());
            dto.setDescription(form.getDescription());
            dto.setType(form.getType());
            dto.setOpen(form.getOpen());
            dto.setClosed(form.getClosed());

            List<QuestionDTO> questionDTOs = new ArrayList<>();
            for (Question question : form.getQuestions()) {
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setText(question.getText());

                List<OptionDTO> optionDTOs = new ArrayList<>();
                for (ROption option : question.getOptions()) {
                    OptionDTO optionDTO = new OptionDTO();
                    optionDTO.setValue(option.getValue());
                    optionDTO.setType(option.getType());
                    optionDTOs.add(optionDTO);
                }

                questionDTO.setOptions(optionDTOs);
                questionDTOs.add(questionDTO);
            }

            dto.setQuestions(questionDTOs);

            return new APIResponse("Formulario encontrado", HttpStatus.OK, false, dto);
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al obtener el formulario");
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse update(Long id, FormDTO payload, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            Form form = formRepository.findByIdAndCreatorEmail(id, email)
                    .orElse(null);
            if (form == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Formulario no encontrado");
            }
            form.setTitle(payload.getTitle());
            form.setDescription(payload.getDescription());
            form.setType(payload.getType());
            form.setOpen(payload.getOpen());
            form.setClosed(payload.getClosed());
            formRepository.save(form);
            return new APIResponse("Formulario actualizado correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al actualizar el formulario");
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public APIResponse delete(Long id, HttpServletRequest req) {
        try {
            String email = jwtUtils.resolveClaims(req, "sub");
            Form form = formRepository.findByIdAndCreatorEmail(id, email)
                    .orElse(null);
            if (form == null) {
                return new APIResponse(HttpStatus.NOT_FOUND, true, "Formulario no encontrado");
            }
            formRepository.delete(form);
            return new APIResponse("Formulario eliminado correctamente", HttpStatus.OK, false, "");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.BAD_REQUEST, true, "Error al eliminar el formulario");
        }
    }
}
