package utez.edu.mx.florever.modules.form;

import jakarta.persistence.*;
import utez.edu.mx.florever.modules.question.Question;
import utez.edu.mx.florever.modules.user.BeanUser;

import java.util.List;

@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String description;
    private String type;
    private String url;

    @ManyToOne
    @JoinColumn(name = "fk_creator")
    private BeanUser creator;

    @OneToMany(mappedBy = "form")
    private List<Question> questions;

    public Form() {

    }

    public BeanUser getCreator() {
        return creator;
    }

    public void setCreator(BeanUser creator) {
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Form(List<Question> questions, BeanUser creator, String url, String type, String description, String title, Long id) {
        this.questions = questions;
        this.creator = creator;
        this.url = url;
        this.type = type;
        this.description = description;
        this.title = title;
        this.id = id;
    }
}
