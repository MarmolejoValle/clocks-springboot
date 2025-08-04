package utez.edu.mx.florever.modules.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.florever.modules.form.Form;
import utez.edu.mx.florever.modules.option.ROption;

import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_form")
    private Form form;

    private String text;

    @OneToMany(mappedBy = "question")
    private List<ROption> ROptions;

    public Question() {

    }

    public List<ROption> getOptions() {
        return ROptions;
    }

    public void setOptions(List<ROption> ROptions) {
        this.ROptions = ROptions;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question(Long id, Form form, String text, List<ROption> ROptions) {
        this.id = id;
        this.form = form;
        this.text = text;
        this.ROptions = ROptions;
    }
}
