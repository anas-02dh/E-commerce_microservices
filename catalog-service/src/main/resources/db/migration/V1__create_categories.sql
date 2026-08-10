CREATE TABLE categories (
                            id UUID PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            description VARCHAR(500)
);

CREATE TABLE products (
                          id UUID PRIMARY KEY,
                          name VARCHAR(150) NOT NULL,
                          description VARCHAR(1000),
                          available_quantity INTEGER NOT NULL,
                          price NUMERIC(19, 2) NOT NULL,
                          category_id UUID NOT NULL,

                          CONSTRAINT fk_product_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id),

                          CONSTRAINT chk_product_quantity
                              CHECK (available_quantity >= 0),

                          CONSTRAINT chk_product_price
                              CHECK (price >= 0)
);

CREATE INDEX idx_products_category_id
    ON products(category_id);