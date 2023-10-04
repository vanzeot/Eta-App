package com.vanz.eta.repository;

import com.vanz.eta.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "equipment", path = "equipment")
// Without this @ above the name will be shown as "employeeS", because it puts an "s" at the end by default
public interface EquipmentRepository  extends JpaRepository<Equipment,Long> {
}
