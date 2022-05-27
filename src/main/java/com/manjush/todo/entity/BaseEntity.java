package com.manjush.todo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_time", updatable = false)
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(updatedTime, that.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdTime, updatedTime);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }

    @PrePersist
    public void prePersist() {
        this.updatedTime = this.createdTime = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedTime = Instant.now();
    }
}
