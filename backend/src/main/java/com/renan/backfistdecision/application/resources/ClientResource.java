package com.renan.backfistdecision.application.resources;

import com.renan.backfistdecision.domain.entity.Client;
import com.renan.backfistdecision.domain.exception.model.ErrorDetail;
import com.renan.backfistdecision.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/api/client")
@Tag(name = "Client", description = "Api Client operations")
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Get all clients list")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Success",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))}),
    })
    @GetMapping(value = "/list")
    private ResponseEntity<List<Client>> findAllProfiles(){
        return ResponseEntity.ok(
                clientService.find()
        );
    }

    @Operation(summary = "Get client by id")
    @GetMapping(value = "/{id}")
    private ResponseEntity<Client> findById(@PathVariable(value = "id") Long id ) {
        return ResponseEntity.ok(
                clientService.findById(id)
        );
    }

    @Operation(summary = "Create new clients")
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createField(@Valid  @RequestBody Client client) {
        clientService.create(client);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(location).body(client);
    }

    @Operation(summary = "Update client")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateForm(@Valid @RequestBody Client client) {
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete client")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable("id") Long id) {
        clientService.remove(id);

        return ResponseEntity.noContent().build();
    }
}
