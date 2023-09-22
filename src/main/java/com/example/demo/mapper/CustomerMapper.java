package com.example.demo.mapper;

import com.example.demo.dao.entity.CustomerEntity;
import com.example.demo.model.dto.CustomerRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CustomerMapper {
    public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    public abstract CustomerEntity dtoToEntity(CustomerRequestDTO customerRequestDTO);
}
