package com.damon.oauth.domain.user.aggregate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Invoice {
    private final InvoiceId invoiceId;
}
