package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Pais;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.PaisRepository;

@Repository
public class PaisRepositoryImpl extends BaseRepositoryImpl<Pais, String> implements PaisRepository{
}
