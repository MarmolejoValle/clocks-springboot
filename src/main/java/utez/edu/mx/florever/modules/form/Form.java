package utez.edu.mx.florever.modules.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.florever.modules.question.Question;
import utez.edu.mx.florever.modules.user.BeanUser;

import java.time.LocalDateTime;
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
    private LocalDateTime created;
    private LocalDateTime open;
    private LocalDateTime closed;
    private LocalDateTime expired;
    @JsonIgnore
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Form(List<Question> questions, BeanUser creator, String type, String description, String title, Long id) {
        this.questions = questions;
        this.creator = creator;
        this.type = type;
        this.description = description;
        this.title = title;
        this.id = id;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public LocalDateTime getClosed() {
        return closed;
    }

    public void setClosed(LocalDateTime closed) {
        this.closed = closed;
    }

    public LocalDateTime getOpen() {
        return open;
    }

    public void setOpen(LocalDateTime open) {
        this.open = open;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
