package com.dev.logistics.api.controller;

import com.dev.logistics.domain.model.Client;
import com.dev.logistics.domain.repository.ClientRepository;
import com.dev.logistics.domain.service.CustomerCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @author rodrigoqueiroz
 */

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@Validated
public class ClientController {

    private final ClientRepository clientRepository;
    private final CustomerCatalogService customerCatalogService;

    @GetMapping("/all")
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> search(Long clientId) {
        return clientRepository.findById(clientId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@Valid @RequestBody Client client) {
        return customerCatalogService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@PathVariable Long clientId, @Valid @RequestBody Client client) {
        if (!clientRepository.existsById(clientId))
            return ResponseEntity.notFound().build();

        client.setId(clientId);
        client = customerCatalogService.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        if (!clientRepository.existsById(clientId))
            return ResponseEntity.notFound().build();

        customerCatalogService.delete(clientId);

        return ResponseEntity.noContent().build();
    }

}
