package com.mathffreitas.jpaunipds.controller.common;

import com.mathffreitas.jpaunipds.mapper.common.CreateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.mapper.common.EntityToDtoMapper;
import com.mathffreitas.jpaunipds.mapper.common.UpdateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.model.entity.common.BaseEntity;
import com.mathffreitas.jpaunipds.service.common.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController<
        E extends BaseEntity,
        D extends RepresentationModel<D>,
        C,
        U,
        S extends BaseService<E>,
        M extends EntityToDtoMapper<E, D> & CreateDtoToEntityMapper<C, E> & UpdateDtoToEntityMapper<U, E>,
        A extends RepresentationModelAssembler<D, D>> {

    protected final S service;
    protected final M mapper;
    protected final A assembler;

    protected BaseController(S service, M mapper, A assembler) {
        this.service = service;
        this.mapper = mapper;
        this.assembler = assembler;
    }

    @Operation(summary = "List resources", description = "Returns a paginated collection with HATEOAS navigation links")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resources retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<PagedModel<D>> findAll(
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable,
            @Parameter(hidden = true) PagedResourcesAssembler<D> pagedResourcesAssembler) {
        Page<E> page = service.findAll(pageable);
        Page<D> dtoPage = page.map(entity -> mapper.toDto(entity));
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(dtoPage, assembler));
    }

    @Operation(summary = "Get resource by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resource found"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(mapper.toDto(service.findById(id))));
    }

    @Operation(summary = "Create resource")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Resource created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<D> create(@RequestBody C dto) {
        E entity = service.create(mapper.fromCreateDto(dto));
        D response = assembler.toModel(mapper.toDto(entity));
        return ResponseEntity.created(response.getRequiredLink("self").toUri()).body(response);
    }

    @Operation(summary = "Update resource")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resource updated"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable Long id, @RequestBody U dto) {
        E entity = service.update(id, mapper.fromUpdateDto(dto));
        return ResponseEntity.ok(assembler.toModel(mapper.toDto(entity)));
    }

    @Operation(summary = "Delete resource")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Resource deleted"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
