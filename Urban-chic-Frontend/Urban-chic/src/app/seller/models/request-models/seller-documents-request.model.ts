import { SellerAddressRequest } from "./seller-address-reqest.model";

export interface SellerDocumentRequest{
    companyName: string;
    companyLogoUrl: string;
    sellerAddress: SellerAddressRequest;
    companyLogoPublicId:string;
    gstNumber:string;
    panNumber: string;
    accountNumber: string;
    ifscCode: string;
    sellerId: string;
}