package com.github.alcemirjunior.clientcrud.repositories;

import com.github.alcemirjunior.clientcrud.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
