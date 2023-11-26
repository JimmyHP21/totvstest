package com.renan.backfistdecision.application.resources;


import com.renan.backfistdecision.domain.entity.Number;
import com.renan.backfistdecision.domain.service.NumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/api/number")
@Tag(name = "Number", description = "Api Number operations")
public class NumberResource {

    private final NumberService numberService;

    public NumberResource(NumberService numberService) {
        this.numberService = numberService;
    }

    @Operation(summary = "Get All numbers")
    @GetMapping(value = "/list")
    private ResponseEntity<List<Number>> findAllProfiles(){
        return ResponseEntity.ok(
                numberService.find()
        );
    }

    @Operation(summary = "Get number by id")
    @GetMapping(value = "/{id}")
    private ResponseEntity<Number> findById(@PathVariable(value = "id") Long id ) {
        return ResponseEntity.ok(
                numberService.findById(id)
        );
    }

    @Operation(summary = "Create new number")
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Number> createField(@Valid @RequestBody Number number) {
        numberService.create(number);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(number.getId()).toUri();

        return ResponseEntity.created(location).body(number);
    }

    @Operation(summary = "Update number")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateForm(@Valid @RequestBody Number number) {
        numberService.update(number);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Number")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable("id") Long id) {
        numberService.remove(id);

        return ResponseEntity.noContent().build();
    }
}
