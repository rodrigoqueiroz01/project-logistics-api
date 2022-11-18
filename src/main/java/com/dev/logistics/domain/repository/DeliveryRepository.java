package com.dev.logistics.domain.repository;

import com.dev.logistics.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rodrigoqueiroz
 */

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> { }
