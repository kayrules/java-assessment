package my.com.rhb.assessment.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delete_flag", nullable = false)
    private Boolean deleteFlag = false;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets delete flag.
     *
     * @return the delete flag
     */
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * Sets delete flag.
     *
     * @param id the delete flag
     */
    public void setDeleteFlag(Boolean bool) {
        this.deleteFlag = bool;
    }
}