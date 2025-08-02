package utez.edu.mx.florever.modules.form.dto;

import java.util.List;

public class QuestionDTO {
    private String text;
    private List<OptionDTO>  options;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }
}
