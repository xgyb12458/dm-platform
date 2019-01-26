package com.damon.product.domain.sku.tool;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

/**
 * @author Damon S.
 */
@Component
@RequiredArgsConstructor
public class SkuValidator {

    private final CommandGateway commandGateway;



}
