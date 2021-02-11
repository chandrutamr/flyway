package com.example.order.connections;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class TenantContext {

    private static final ThreadLocal<Tenant> tenantHolder = new ThreadLocal<>();

    public  static Tenant getTenant() {
        Tenant tenant = tenantHolder.get();
        return Objects.isNull(tenant) ? TenantFinder.getInstance().findById(1) : tenant;

    }

    public static void setTenant(Tenant tenant) {
        tenantHolder.set(tenant);
    }

    public static void clearTenant() {
        tenantHolder.remove();
    }
}
