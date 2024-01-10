package com.example.demo;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FooService implements InitializingBean {
    @Autowired
    FooRepository fooRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        fooRepository.save(new FooEntity(1L, "first comment"));
        fooRepository.save(new FooEntity(2L, "second comment"));
        fooRepository.save(new FooEntity(3L, "third comment"));
    }

    public record FooFilters(String comment) {
    }

    public List<FooEntity> search(FooFilters filters) {
        Specification<FooEntity> specification = getSpecification(filters);
        return fooRepository.findAll(specification);
    }

    private Specification<FooEntity> getSpecification(FooFilters filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> p = new ArrayList<>();

            if (StringUtils.isNotBlank(filters.comment())) {
                p.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(FooEntity_.COMMENT)),
                        "%" + filters.comment() + "%"));
            }

            return criteriaBuilder.and(p.toArray(Predicate[]::new));
        };
    }
}
