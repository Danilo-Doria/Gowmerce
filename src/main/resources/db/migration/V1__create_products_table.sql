-- V1__create_products_table.sql
-- Primera migración del proyecto: crea la tabla base del catálogo.
--
-- Convención de nombres de Flyway: V<version>__<descripcion_con_guiones_bajos>.sql
-- El doble guion bajo "__" es OBLIGATORIO, separa la versión de la descripción.

CREATE TABLE products
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
    stock       INTEGER        NOT NULL DEFAULT 0 CHECK (stock >= 0),
    sku         VARCHAR(50)    NOT NULL,
    active      BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP      NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_products_sku UNIQUE (sku)
);

-- Índice sobre "active": vamos a filtrar por este campo constantemente
-- (el catálogo público solo muestra productos activos), así que conviene indexarlo
-- para que esas consultas no hagan un recorrido completo de la tabla.
CREATE INDEX idx_products_active ON products (active);

-- Índice sobre "sku": aunque ya es UNIQUE (lo cual crea un índice automáticamente),
-- lo dejamos explícito en el comentario para que quede claro por qué es rápido buscar por sku.