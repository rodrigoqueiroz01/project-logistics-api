package com.dev.logistics.domain.service;

import com.dev.logistics.domain.exception.DomainException;
import com.dev.logistics.domain.exception.EntityNotFoundException;
import com.dev.logistics.domain.model.Client;
import com.dev.logistics.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rodrigoqueiroz
 */

@Service
@AllArgsConstructor
public class CustomerCatalogService {

    private final ClientRepository clientRepository;

    public Client search(Long clienteId) {
        return clientRepository.findById(clienteId).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
    }

    @Transactional
    public Client save(Client client) {
        boolean emailInUse = clientRepository.findByEmail(client.getEmail()).stream()
                .anyMatch(existingCustomer -> !existingCustomer.equals(client));

        if (emailInUse)
            throw new DomainException("Já existe um cliente cadastrado com este e-mail. Informe um novo e-mail.");

        return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }

}
