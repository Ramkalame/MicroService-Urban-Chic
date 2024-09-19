export interface Product{
    productId:string;
    productName:string;
    productDescription:string;
    productBrand:string;
    productCategory:string;
    productSubCategory:string;
    productType:string;
    sellerId:string;
    attributeList:Attribute[];
    variantList:Variant[];
    productImageList:ProductImage[];
    active:boolean;
    oneStarCount:number;
    twoStarCount:number;
    threeStarCount:number;
    fourStarCount:number;
    fiveStarCount:number;
}




interface Attribute{
    attributeId:string;
    attributeName:string;
    attributeValue:any;
}

interface Variant{
    variantId:string;
    variantAttributes:VariantAttribute[];
    variantPrice:number;
    variantQuantity:number;
}


interface VariantAttribute{
    variantAttributeId:string;
    variantAttributeName:string;
    variantAttributeValue:any;
}


interface ProductImage{
    productImageId:string;
    publicId:string;
    publicImageUrl:string;
}