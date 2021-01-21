package com.github.alcemirjunior.clientcrud.resources;

import com.github.alcemirjunior.clientcrud.dto.ClientDTO;
import com.github.alcemirjunior.clientcrud.entity.Client;
import com.github.alcemirjunior.clientcrud.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
