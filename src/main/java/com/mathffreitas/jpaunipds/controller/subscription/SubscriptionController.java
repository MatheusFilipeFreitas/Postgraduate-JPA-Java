package com.mathffreitas.jpaunipds.controller.subscription;

import com.mathffreitas.jpaunipds.hateoas.SubscriptionResourceAssembler;
import com.mathffreitas.jpaunipds.mapper.subscription.SubscriptionMapper;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionCreateDto;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionDto;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionUpdateDto;
import com.mathffreitas.jpaunipds.service.subscription.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Subscriptions", description = "Session subscription endpoints")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionResourceAssembler assembler;

    @Operation(summary = "List subscriptions")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subscriptions retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<PagedModel<SubscriptionDto>> findAll(
            @ParameterObject @PageableDefault(size = 20, sort = "createdAt") Pageable pageable,
            @Parameter(hidden = true) PagedResourcesAssembler<SubscriptionDto> pagedResourcesAssembler) {
        Page<SubscriptionDto> subscriptions = subscriptionService.findAll(pageable)
                .map(subscriptionMapper::toDto);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(subscriptions, assembler));
    }

    @Operation(summary = "Get subscription by user and session")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subscription found"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @GetMapping("/users/{userId}/sessions/{sessionId}")
    public ResponseEntity<SubscriptionDto> findById(
            @PathVariable Long userId,
            @PathVariable Long sessionId) {
        return ResponseEntity.ok(assembler.toModel(subscriptionMapper.toDto(
                subscriptionService.findById(userId, sessionId))));
    }

    @Operation(summary = "Create subscription")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Subscription created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<SubscriptionDto> create(@RequestBody SubscriptionCreateDto dto) {
        var entity = subscriptionService.create(subscriptionMapper.fromCreateDto(dto));
        SubscriptionDto response = assembler.toModel(subscriptionMapper.toDto(entity));
        return ResponseEntity.created(response.getRequiredLink("self").toUri()).body(response);
    }

    @Operation(summary = "Update subscription")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subscription updated"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @PutMapping("/users/{userId}/sessions/{sessionId}")
    public ResponseEntity<SubscriptionDto> update(
            @PathVariable Long userId,
            @PathVariable Long sessionId,
            @RequestBody SubscriptionUpdateDto dto) {
        var entity = subscriptionService.update(userId, sessionId, subscriptionMapper.fromUpdateDto(dto));
        return ResponseEntity.ok(assembler.toModel(subscriptionMapper.toDto(entity)));
    }

    @Operation(summary = "Delete subscription")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Subscription deleted"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @DeleteMapping("/users/{userId}/sessions/{sessionId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long userId,
            @PathVariable Long sessionId) {
        subscriptionService.delete(userId, sessionId);
        return ResponseEntity.noContent().build();
    }
}
