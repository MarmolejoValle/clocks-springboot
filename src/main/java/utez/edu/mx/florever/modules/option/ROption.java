package utez.edu.mx.florever.modules.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.florever.modules.question.Question;
import utez.edu.mx.florever.modules.response.Response;

import java.util.List;

@Entity
public class ROption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String value;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_question")
    private Question question;

    @OneToMany(mappedBy = "op")
    private List<Response>  responses;

    private Boolean type;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ROption() {}

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> users) {
        this.responses = users;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
