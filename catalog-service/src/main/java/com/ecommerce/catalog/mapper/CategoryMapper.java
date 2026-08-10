package com.ecommerce.catalog.mapper;

import com.ecommerce.catalog.dto.CategoryDTO;
import com.ecommerce.catalog.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/
 @Component
public class CategoryMapper {

     public CategoryDTO toDTO(Category category) {

         if (category == null) {
             return null;
         }
         CategoryDTO categoryDTO = new CategoryDTO();

         BeanUtils.copyProperties(category,categoryDTO);
         return categoryDTO;
     }

     public Category toEntity(CategoryDTO categoryDTO) {
         if(categoryDTO == null){
             return null;
         }

         Category category = new Category();

         BeanUtils.copyProperties(categoryDTO,category);

         return category;
     }


}
