package com.inventoryProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryProject.models.Color;

/**
 * ColorRepository
 */
public interface ColorRepository extends JpaRepository<Color, Long> {

}
