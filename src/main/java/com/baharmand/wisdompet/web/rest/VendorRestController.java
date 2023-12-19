package com.baharmand.wisdompet.web.rest;

import com.baharmand.wisdompet.service.VendorService;
import com.baharmand.wisdompet.web.exceptions.BadRequestException;
import com.baharmand.wisdompet.web.models.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorRestController {
    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<Vendor> getVendors() {
        return this.vendorService.getVendors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor addVendor(@RequestBody Vendor model) {
        return this.vendorService.createOrUpdateVendor(model);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable("id") long id) {
        return this.vendorService.getVendor(id);
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable("id") Long id, @RequestBody Vendor model) {
        if (id != model.getVendorId()) {
            throw new BadRequestException("incoming id doesn't match path");
        }
        return this.vendorService.createOrUpdateVendor(model);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteVendor(@PathVariable("id") long id) {
        this.vendorService.deleteVendor(id);
    }
}