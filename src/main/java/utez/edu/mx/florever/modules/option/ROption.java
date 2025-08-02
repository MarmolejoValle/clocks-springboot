package utez.edu.mx.florever.modules.option;

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

    @ManyToOne
    @JoinColumn(name = "fk_question")
    private Question question;

    @OneToMany(mappedBy = "ROption")
    private List<Response>  users;

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

    public List<Response> getUsers() {
        return users;
    }

    public void setUsers(List<Response> users) {
        this.users = users;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
