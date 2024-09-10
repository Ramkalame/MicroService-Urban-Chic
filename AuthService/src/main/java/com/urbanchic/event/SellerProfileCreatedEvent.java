package com.urbanchic.event;

import com.urbanchic.external.SellerDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SellerProfileCreatedEvent extends ApplicationEvent {
        private SellerDto sellerDto;
    public SellerProfileCreatedEvent(Object source,SellerDto sellerDto) {
        super(source);
        this.sellerDto = sellerDto;

    }
}
