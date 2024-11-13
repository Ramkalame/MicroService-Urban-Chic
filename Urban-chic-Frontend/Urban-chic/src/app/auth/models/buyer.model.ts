export interface Buyer{
    id: string;
    buyerId: string;
    name?: string;
    gender: string;
    email?: string;
    phoneNumber: string;
    role: string;
    addressList:Address [];
}

export interface Address{
    id: string;
    houseNumber: string;
    streetName: string;
    landmark: string;
    city: string;
    district: string;
    state: string;
    country: string;
    pinCode: string;
    addressLabel: string;
}

export interface BuyerRegistrationRequest{
    phoneNumber:string;
}

export interface BuyerLoginRequest{
    phoneNumber:string;
}

export interface BuyerDetailsUpdateRequest{
    buyerId: string;
    name: string;
    gender: string;
    email: string;
    phoneNumber: string;
}