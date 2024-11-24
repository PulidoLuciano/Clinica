package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Recepcionista;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.RecepcionistaRepository;

@Repository
public class RecepcionistaRepositoryImpl extends BaseRepositoryImpl<Recepcionista, Long> implements RecepcionistaRepository{

}
