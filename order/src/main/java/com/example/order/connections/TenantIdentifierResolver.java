package com.example.order.connections;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver  implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        return TenantContext.getTenant().getName();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
