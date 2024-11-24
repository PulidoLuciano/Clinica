package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Usuario;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.UsuariosRepository;

@Repository
public class UsuariosRepositoryImpl extends BaseRepositoryImpl<Usuario, String> implements UsuariosRepository{

}
