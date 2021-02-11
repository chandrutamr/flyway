package com.example.product.connections;

import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TenantIdentifierConfig implements HandlerInterceptor {

    private TenantFinder tenantFinder;

    @Autowired
    private RoutingDataSource routingDataSource;

    @Autowired
    private DataBaseConfiguration databaseConfiguration;

    @Autowired
    private FlywayMigrationInitializer flywayMigrationInitializer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String tenant = request.getParameter("tenant");
        tenantFinder = TenantFinder.getInstance();
        if(tenantFinder.findByName(tenant)==null){
            tenantFinder.loadRedisIntoMap();
            routingDataSource.initDataSources(databaseConfiguration);
            flywayMigrationInitializer.migrate(tenant);
        }
        TenantContext.setTenant(tenantFinder.findByName(tenant));
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, @Nullable Exception ex) throws Exception {
        TenantContext.clearTenant();
    }

}
