package com.dev.logistics.domain.model;

import com.dev.logistics.domain.exception.DomainException;
import com.dev.logistics.domain.model.enums.DeliveryStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {

    @Serial
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Embedded
    private Destiny destiny;

    private BigDecimal tax;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrences = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private OffsetDateTime orderDate;

    private OffsetDateTime completionDate;

    public Occurrence addOccurrence(String description) {
        var occurrence = Occurrence
                .builder()
                .description(description)
                .registrationDate(OffsetDateTime.now())
                .delivery(this)
                .build();

        this.getOccurrences().add(occurrence);

        return occurrence;
    }

    public void finalize() {
        if(notCanFinish())
            throw new DomainException("Entrega não pode ser finalizada.");

        setStatus(DeliveryStatus.FINALIZADA);
        setCompletionDate(OffsetDateTime.now());
    }

    public boolean canFinish() {
        return DeliveryStatus.PENDENTE.equals(getStatus());
    }

    public boolean notCanFinish() {
        return !canFinish();
    }

}
