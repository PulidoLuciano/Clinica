package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.ObraSocial;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.ObraSocialRepository;

@Repository
public class ObraSocialRepositoryImpl extends BaseRepositoryImpl<ObraSocial, String> implements ObraSocialRepository{

}
