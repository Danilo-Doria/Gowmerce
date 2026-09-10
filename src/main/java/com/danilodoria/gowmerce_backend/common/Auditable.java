package com.danilodoria.gowmerce_backend.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @MappedSuperclass le dice a JPA que esta clase no es una entidad ni tiene su propia tabla, pero sus campos
 * deben heredarse como columnas en cualquier clase que extienda de ella". Es herencia a nivel de mapeo ORM.
 * @EntityListeners(AuditingEntityListener.class) conecta esta clase con el mecanismo de auditoría que se activó
 * en el main(@SpringBootApplication). Este listener es quien realmente "escucha" los eventos de guardar/actualizar
 * y rellena los campos.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    // Spring Data JPA rellena este campo automáticamente una sola vez, en el momento del primer INSERT
    // updatable = false es una capa extra de seguridad, Hibernate ignora cambios para esa columna específica
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // @LastModifiedDate se rellena en el INSERT inicial, y se vuelve a actualizar en cada UPDATE posterior
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
