/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Controller
public class InventoryRestController {
    private static final Logger LOG = LoggerFactory
            .getLogger(InventoryRestController.class);
    private final InventoryService inventoryService;

    public InventoryRestController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(value = "/inventories",
            produces = { "application/json" })
    public ResponseEntity<Page<Inventory>> search(String name,
            Pageable pageable) {
        LOG.info("{}", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return ResponseEntity.ok(inventoryService.search(name, pageable));
    }
}
