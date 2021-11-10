package com.metalsa.supplier.services;

import com.metalsa.supplier.repository.SpTblCatalogoItemDocRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpTblCatalogoItemDocService {
   
    @Autowired
    private SpTblCatalogoItemDocRepository spTblCatalogoItemDocRepository;

    // public findDocsByIdItem(Long idItem){}
}
