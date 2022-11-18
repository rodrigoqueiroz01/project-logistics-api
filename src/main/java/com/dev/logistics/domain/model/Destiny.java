package com.dev.logistics.domain.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
@Embeddable
public class Destiny implements Serializable {

    @Serial
    public static final long serialVersionUID = 1L;

    @NotBlank
    @Column(name = "destiny_name")
    private String name;

    @NotBlank
    @Column(name = "destiny_address")
    private String address;

    @NotBlank
    @Column(name = "destiny_number")
    private Integer number;

    @Column(name = "destiny_complement")
    private String complement;

    @NotBlank
    @Column(name = "destiny_district")
    private String district;

}
