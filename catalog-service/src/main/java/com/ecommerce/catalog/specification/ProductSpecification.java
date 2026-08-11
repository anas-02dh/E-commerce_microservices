package com.ecommerce.catalog.specification;

import com.ecommerce.catalog.dto.ProductFilter;
import com.ecommerce.catalog.entity.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author {ANAS DR}
 **/
public class ProductSpecification {

    public static Specification<Product> withFilters(
            ProductFilter filter
    ) {

        return (root, query, criteriaBuilder) -> {

            var predicates = criteriaBuilder.conjunction();


            // Filter by name
            // WHERE LOWER(product.name) LIKE '%phone%'

            if (filter.getName() != null &&
                    !filter.getName().isBlank()) {

                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filter.getName().toLowerCase() + "%"
                        )
                );
            }


            // Filter by category
            // Query : FROM product p INNER JOIN category c ON p.category_id = c.id
            // INNER JOIN category
            // WHERE LOWER(category.name) = 'electronics'

            if (filter.getCategoryName() != null &&
                    !filter.getCategoryName().isBlank()) {

                Join<Object, Object> category =
                        root.join("category", JoinType.INNER);

                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(category.get("name")),
                                filter.getCategoryName().toLowerCase()
                        )
                );
            }


            // Minimum price
            // WHERE product.price >= 100

            if (filter.getMinPrice() != null) {

                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("price"),
                                filter.getMinPrice()
                        )
                );
            }


            // Maximum price
            // WHERE product.price <= 1000

            if (filter.getMaxPrice() != null) {

                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("price"),
                                filter.getMaxPrice()
                        )
                );
            }

            return predicates;
        };
    }
}

