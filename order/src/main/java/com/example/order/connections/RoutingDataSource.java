package com.example.order.connections;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final Map<Object, Object> dataSourceMap = new HashMap<>();

    private TenantFinder tenantFinder;

    @Value("${application.module.name}")
    String moduleName;

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenant();
    }

    RoutingDataSource(){
        tenantFinder = TenantFinder.getInstance();
        Objects.requireNonNull(tenantFinder);
    }

    void initDataSources(DataBaseConfiguration configuration) {
        if(tenantFinder.getAllTenants()==null || tenantFinder.getAllTenants().size()==0){
            tenantFinder.loadRedisIntoMap();
        }
        for (Tenant tenant : tenantFinder.getAllTenants()) {
            if(dataSourceMap!=null && dataSourceMap.get(tenant)==null){
                dataSourceMap.put(tenant, new HikariDataSource(hikariConfig(tenant, configuration)));
            }
        }
        setDefaultTargetDataSource(dataSourceMap.get(tenantFinder.findById(1)));
        setTargetDataSources(dataSourceMap);
    }

    DataSource getDataSourceByTenant(Tenant tenant) {
        return (DataSource) dataSourceMap.get(tenant);
    }

    DataSource getDefaultDataSource() {
        //Tenant defaultTenant = new Tenant(0,"default");
        return getDataSourceByTenant(tenantFinder.findById(1));
    }

    DataSource getDefaultDataSource( DataBaseConfiguration configuration) {
        initDataSources(configuration);
        return getDataSourceByTenant(tenantFinder.findById(1));
    }

    private HikariConfig hikariConfig(Tenant tenant,
                                      DataBaseConfiguration configuration) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(configuration.getUrl().replace("tenantName", tenant.getName()));
        hikariConfig.setUsername(configuration.getUser());
        hikariConfig.setPassword(configuration.getPassword());
        hikariConfig.setAutoCommit(Boolean.FALSE);
        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", Boolean.TRUE);
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", 2048);
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", Boolean.TRUE);
        hikariConfig.addDataSourceProperty("dataSource.useLocalSessionState", Boolean.TRUE);
        hikariConfig.addDataSourceProperty("dataSource.rewriteBatchedStatements", Boolean.TRUE);
        hikariConfig.addDataSourceProperty("dataSource.cacheResultSetMetadata", Boolean.TRUE);
        hikariConfig.addDataSourceProperty("dataSource.cacheServerConfiguration", Boolean.TRUE);
        hikariConfig.addDataSourceProperty("dataSource.maintainTimeStats", Boolean.FALSE);
        return hikariConfig;
    }
}
