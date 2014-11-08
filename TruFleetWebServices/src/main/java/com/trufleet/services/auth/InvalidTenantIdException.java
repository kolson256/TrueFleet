package com.trufleet.services.auth;

/**
 * Created by Kyle Olson on 11/7/2014.
 */
public class InvalidTenantIdException extends Exception {

    private String tenantId;

    public InvalidTenantIdException() {
        super();
    }

    public InvalidTenantIdException(String message, String tenantId) {
        super(message);
        this.tenantId = tenantId;
    }

    public InvalidTenantIdException(String message, String tenantId, Throwable cause) {
        super(message, cause);
        this.tenantId = tenantId;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " - TenantId : [" + tenantId + "]";
    }

    public String getTenantId() { return tenantId; }
}
