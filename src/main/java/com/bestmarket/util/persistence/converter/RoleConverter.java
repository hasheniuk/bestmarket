package com.bestmarket.util.persistence.converter;

import com.bestmarket.entity.Role;

import javax.persistence.*;

@Converter
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return role.getId();
    }

    @Override
    public Role convertToEntityAttribute(Integer id) {
        return Role.of(id);
    }
}
