export interface ProductRequest{
    productName:string;
    productDescription:string;
    productBrand:string;
    productCategory:string;
    productSubCategory:string;
    productType:string;
    sellerId:string;
    attributeDtoList:AttributeRequsest[];
    variantDtoList:VariantRequest[];
}




interface AttributeRequsest{
    attributeName:string;
    attributeValue:any;
}

interface VariantRequest{
    variantAttributeDtoList:VariantAttributeRequest[];
    variantPrice:number;
    variantQuantity:number;
}


interface VariantAttributeRequest{
    variantAttributeName:string;
    variantAttributeValue:any;
}
