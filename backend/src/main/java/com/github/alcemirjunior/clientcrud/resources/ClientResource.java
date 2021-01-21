package com.github.alcemirjunior.clientcrud.resources;

import com.github.alcemirjunior.clientcrud.dto.ClientDTO;
import com.github.alcemirjunior.clientcrud.entity.Client;
import com.github.alcemirjunior.clientcrud.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO clientDTO = service.findById(id);
        return ResponseEntity.ok().body(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO){
        clientDTO = service.insert(clientDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(clientDTO);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        clientDTO = service.update(id, clientDTO);
        return ResponseEntity.ok().body(clientDTO);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
