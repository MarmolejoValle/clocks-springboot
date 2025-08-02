package utez.edu.mx.florever.modules.response;

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

    @ManyToOne
    @JoinColumn(name = "fk_option")
    private ROption ROption;

    public ROption getOption() {
        return ROption;
    }

    public void setOption(ROption ROption) {
        this.ROption = ROption;
    }

}
