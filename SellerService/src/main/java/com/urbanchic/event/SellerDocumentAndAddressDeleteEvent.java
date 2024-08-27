package com.urbanchic.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SellerDocumentAndAddressDeleteEvent extends ApplicationEvent {
    private String sellerId;
    public SellerDocumentAndAddressDeleteEvent(Object source,String sellerId) {
        super(source);
        this.sellerId = sellerId;
    }
}
