/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.inventory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.dias.tenant.ReadsTenantData;
import id.dias.tenant.TenantService;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Service
@Transactional(readOnly = true)
public class InventoryServiceImpl extends TenantService
        implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @ReadsTenantData
    public Page<Inventory> search(String name, Pageable pageable) {
        return inventoryRepository.search(name, pageable);
    }

}
