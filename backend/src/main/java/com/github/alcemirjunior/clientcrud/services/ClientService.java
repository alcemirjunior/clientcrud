package com.github.alcemirjunior.clientcrud.services;

import com.github.alcemirjunior.clientcrud.dto.ClientDTO;
import com.github.alcemirjunior.clientcrud.entity.Client;
import com.github.alcemirjunior.clientcrud.repositories.ClientRepository;
import com.github.alcemirjunior.clientcrud.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pagedRequest){
        Page<Client> list = repository.findAll(pagedRequest);
        return list.map(x->new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> obj = repository.findById(id);
        Client client = obj.orElseThrow(()->new ResourceNotFoundException("Client not found"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){
        Client client = new Client();
        copyDtoToEntity(clientDTO, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO){
        try {
            Client client = repository.getOne(id);
            copyDtoToEntity(clientDTO, client);
            client = repository.save(client);
            return new ClientDTO(client);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete (Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }


    //Função auxiliar - copia o que esta no clientDTO para client

    private void copyDtoToEntity(ClientDTO clientDTO, Client client){
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }
}
