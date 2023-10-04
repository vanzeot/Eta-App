package com.vanz.eta.repository;

import com.vanz.eta.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LocationRepository  extends JpaRepository<Location,Long> {
}
