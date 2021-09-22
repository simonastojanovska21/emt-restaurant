package com.example.customer.domain.model;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class CustomerId extends DomainObjectId {

    private CustomerId() {
        super(CustomerId.randomId(CustomerId.class).getId());
    }

    public CustomerId(@NonNull String uuid) {
        super(uuid);
    }
}
