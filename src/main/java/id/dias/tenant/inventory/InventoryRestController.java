/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.inventory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Controller
public class InventoryRestController {
    private final InventoryService inventoryService;

    public InventoryRestController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(value = "/inventories",
            produces = { "application/json" })
    public ResponseEntity<Page<Inventory>> search(String name,
            Pageable pageable) {
        return ResponseEntity.ok(inventoryService
                .search(name, pageable));
    }
}
