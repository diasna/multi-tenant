/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.inventory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    @Query("SELECT i FROM Inventory i WHERE i.name LIKE CONCAT('%',:name,'%')")
    Page<Inventory> search(@Param("name") String name, Pageable pageable);
}
