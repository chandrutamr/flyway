package com.example.product.connections;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class MultitenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Autowired
    private RoutingDataSource routingDataSource;

    @Override
    protected DataSource selectAnyDataSource() {
        return routingDataSource.getDefaultDataSource();
    }

    @Override
    protected DataSource selectDataSource(String tenantName) {
        return routingDataSource.getDataSourceByTenant(TenantFinder.getInstance().findByName(tenantName));
    }
}
