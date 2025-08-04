package utez.edu.mx.florever.modules.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.florever.modules.option.ROption;
import utez.edu.mx.florever.modules.user.BeanUser;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private BeanUser user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_option")
    private ROption op;

    public ROption getOp() {
        return op;
    }

    public void setOp(ROption ROption) {
        this.op = ROption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BeanUser getUser() {
        return user;
    }

    public void setUser(BeanUser user) {
        this.user = user;
    }

}
