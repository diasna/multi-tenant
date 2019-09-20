/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.inventory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
public interface InventoryService {
    Inventory create(Inventory inventory);

    Page<Inventory> search(String name, Pageable pageable);

    Page<Inventory> searchWithPreDefinedTenant(String tenantId, String name,
            Pageable pageable);
}
