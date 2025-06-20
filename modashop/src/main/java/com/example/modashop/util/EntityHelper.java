package com.example.modashop.util;

import com.example.modashop.entity.Category;
import com.example.modashop.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class EntityHelper {
    public <T, ID> T findOrThrowException(Class<T> entityClass, ID id, JpaRepository<T, ID> repository) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass, (Long) id));
    }

}
