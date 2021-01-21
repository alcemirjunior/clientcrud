package com.github.alcemirjunior.clientcrud.services;

import com.github.alcemirjunior.clientcrud.dto.ClientDTO;
import com.github.alcemirjunior.clientcrud.entity.Client;
import com.github.alcemirjunior.clientcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll(){
        List<Client> list = repository.findAll();
        return list.stream().map(x->new ClientDTO(x)).collect(Collectors.toList());
    }
}
