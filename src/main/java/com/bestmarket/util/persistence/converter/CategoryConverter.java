package com.bestmarket.util.persistence.converter;

import com.bestmarket.entity.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Category category) {
        return category.getId();
    }

    @Override
    public Category convertToEntityAttribute(Integer id) {
        return Category.of(id);
    }
}
