package com.renan.backfistdecision.domain.service;


import com.renan.backfistdecision.domain.entity.Client;
import com.renan.backfistdecision.domain.entity.Number;
import com.renan.backfistdecision.domain.exception.EntityNotFoundException;
import com.renan.backfistdecision.domain.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renan on 23/11/2023.
 */
@Service
public class ClientService implements Serializable {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> find() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Client system) {
        clientRepository.save(system);
    }

    @Transactional
    public void update(Client client) {
        Client c = clientRepository.findById(client.getId()).orElseThrow(EntityNotFoundException::new);
        c.setAddress(client.getAddress());
        c.setCpf(client.getCpf());
        c.setNeighborhood(client.getNeighborhood());
        c.setName(client.getName());

        List<Number> list = new ArrayList<>();
        c.getNumberList().forEach( num -> {
            client.getNumberList().forEach( n -> {
                if (n != num) {
                    list.add(n);
                }
            });
        });

        c.setNumberList(list);

        clientRepository.save(c);
    }

    @Transactional
    public void remove(Long id) {
        clientRepository.deleteById(id);
    }
}
