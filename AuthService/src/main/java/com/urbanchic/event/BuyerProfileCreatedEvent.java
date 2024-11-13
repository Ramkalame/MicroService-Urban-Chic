package com.urbanchic.event;

import com.urbanchic.external.BuyerDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class BuyerProfileCreatedEvent extends ApplicationEvent {

    private BuyerDto buyerDto;

    public BuyerProfileCreatedEvent(Object source,BuyerDto buyerDto) {
        super(source);
        this.buyerDto = buyerDto;
    }

}
