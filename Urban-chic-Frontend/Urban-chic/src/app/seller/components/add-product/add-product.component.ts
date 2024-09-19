import { CommonModule } from '@angular/common';
import { Component, inject, Input, OnInit } from '@angular/core';
import { Form, FormArray, FormBuilder, FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ProductService } from '../../../common/services/product.service';
import { ProductRequest } from '../../../common/models/product-models/product-request.model';
import { Category, productCategories } from '../../../core/models/product-categories-subcategories.model';
import { SnackbarService } from '../../../common/services/snackbar.service';
import { ApiResponse } from '../../../core/models/shared-models/api-response.model';
import { Product } from '../../../common/models/product-models/product-response.model';
import { HttpErrorResponse } from '@angular/common/http';
import { SellerAuthService } from '../../../core/services/seller-auth.service';


type ProductAttribute = FormGroup<{
  attributeName: FormControl<string>,
  attributeValue: FormControl<string>
}>;

type VariantsAttribute = FormGroup<{
  variantAttributeName: FormControl<string>,
  variantAttributeValue: FormControl<string>,
}>;

type ProductVariants = FormGroup<{
  variantAttributes: FormArray<VariantsAttribute>,
  variantPrice: FormControl<number | null>,
  variantQuantity: FormControl<number | null>
}>;

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [MatIconModule, MatInputModule, MatFormFieldModule, MatSelectModule, MatDividerModule, MatButtonModule,
    ReactiveFormsModule, CommonModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnInit {

  @Input() heading = 'Add New Product';
  @Input() showSubmitBtn = true;
  @Input() showCancelBtn = true;

  productService = inject(ProductService);
  sellerAuthService = inject(SellerAuthService);
  fb = inject(NonNullableFormBuilder);
  snackBar = inject(SnackbarService);

  //product images
  previewUrls: string[] = [];  // Holds the preview URLs for all selected files
  selectedFiles: File[] = [];  // Holds the selected files

  productForm = this.fb.group({
    productName: new FormControl<string>('',Validators.required),
    productDescription: new FormControl<string>('',Validators.required),
    productBrand: new FormControl<string>('',Validators.required),
    productCategory: new FormControl<string>('',Validators.required),
    productSubcategory: new FormControl<string>('',Validators.required),
    productType: new FormControl<string>('',Validators.required),
    productAttributes: this.fb.array<ProductAttribute>([this.generateProductAttribute()]),
    productVariants: this.fb.array<ProductVariants>([this.generateProductVariants()])
  })

  categories: Category[] = productCategories;
  selectedCategory!: string | null;
  selectedSubcategory!: string | null;
  subcategories: string[] = [];
  types: string[] = [];

  userId!:string;



  constructor() { }
  ngOnInit(): void {

    this.userId = this.sellerAuthService.getUserId();

    this.productForm.get('productCategory')!.valueChanges.subscribe(category => {
      this.selectedCategory = category;
      this.getProductSubcategory()
    })

    this.productForm.get('productSubcategory')!.valueChanges.subscribe(subcategory => {
      this.selectedSubcategory = subcategory;
      this.getProductTypes()
    })
  }

  getProductSubcategory() {
    this.subcategories = this.categories.find(c => c.categoryName === this.selectedCategory)!.subcategories.map(s => s.subcategoryName);
  }

  getProductTypes() {
    this.types = this.categories.find(c => c.categoryName === this.selectedCategory)!.subcategories.find(s => s.subcategoryName === this.selectedSubcategory)!.types;
  }



  generateProductAttribute(): ProductAttribute {
    return this.fb.group({
      attributeName: ['',Validators.required],
      attributeValue: ['',Validators.required]
    })
  }

  generateProductVariants(): FormGroup {
    return this.fb.group({
      variantAttributes: this.fb.array<VariantsAttribute>([this.generateVariantsAttribute()]),
      variantPrice: [null,Validators.required],
      variantQuantity: [null,Validators.required]
    })
  }

  generateVariantsAttribute(): VariantsAttribute {
    return this.fb.group({
      variantAttributeName: ['',Validators.required],
      variantAttributeValue: ['',Validators.required]
    })
  }

  addProductAttribute() {
    this.productForm.controls.productAttributes.push(this.generateProductAttribute())
  }

  removeProductAttribute(productAttributeIndex: number) {
    this.productForm.controls.productAttributes.removeAt(productAttributeIndex);
  }

  addProductVariants() {
    this.productForm.controls.productVariants.push(this.generateProductVariants())
  }

  removeProductVariants(variantsIndex: number) {
    this.productForm.controls.productVariants.removeAt(variantsIndex);
  }

  addVariantAttributes(variantIndex: number) {
    this.productForm.controls.productVariants.at(variantIndex).controls.variantAttributes.push(this.generateVariantsAttribute())
  }

  removeVariantAttributes(variantIndex: number, variantAttributeIndex: number) {
    this.productForm.controls.productVariants.at(variantIndex).controls.variantAttributes.removeAt(variantAttributeIndex);
  }


  //image preview
  // Triggered when a file is selected
  onFilesSelected(event: Event): void {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
      // Clear the previous selection
      // this.selectedFiles = [];
      // this.previewUrls = [];

      // Iterate through each selected file
      Array.from(fileInput.files).forEach(file => {
        this.selectedFiles.push(file);

        // Create a FileReader for each file
        const reader = new FileReader();
        reader.onload = () => {
          // Store the result (base64 URL of the image) in previewUrls array
          if (reader.result) {
            this.previewUrls.push(reader.result.toString());
          }
        };

        // Read the selected file as a Data URL (base64 encoded)
        reader.readAsDataURL(file);
      });
    }
  }

  removeProductImage(index: number) {
    this.previewUrls.splice(index, 1);
    this.selectedFiles.splice(index, 1);
  }

  submitProductForm() {
    // Extract form values
    const formValue = this.productForm.value;

    // Convert form values to the format required by ProductRequest
    const formData: ProductRequest = {
      productName: formValue.productName ?? '',
      productDescription: formValue.productDescription ?? '',
      productBrand: formValue.productBrand ?? '',
      productCategory: formValue.productCategory ?? '',
      productSubCategory: formValue.productSubcategory ?? '',
      productType: formValue.productType ?? '',
      sellerId: this.userId, // Replace with actual seller ID as needed
      attributeDtoList: (formValue.productAttributes ?? []).map((attribute: any) => ({
        attributeName: attribute.attributeName ?? '',  // Provide default value
        attributeValue: attribute.attributeValue ?? ''
      })),
      variantDtoList: (formValue.productVariants ?? []).map((variant: any) => ({
        variantAttributeDtoList: (variant.variantAttributes ?? []).map((attr: any) => ({
          variantAttributeName: attr.variantAttributeName ?? '',  // Provide default value
          variantAttributeValue: attr.variantAttributeValue ?? ''
        })),
        variantPrice: variant.variantPrice ?? 0,  // Provide default value
        variantQuantity: variant.variantQuantity ?? 0
      })),
    };

    console.log(formData);
    this.productService.addProduct(formData).subscribe({
      next: (res:ApiResponse<Product>) => {
        console.log('Product added successfully');
        this.productForm.reset();
        this.snackBar.openSuccessSnackBar(res.message);
        this.uploadProductImage(res.data.productId); 
      },
      error: (error:HttpErrorResponse) => {
        this.snackBar.openFailedSnackBar(error);
      }
    });
  }


  uploadProductImage(productId:string){
    const formData = new FormData;
    this.selectedFiles.forEach(file => {
      formData.append('files', file);
    });
    this.productService.uploadProductImage(productId, formData).subscribe({
      next: (res:ApiResponse<any>) => {
        console.log('Product images uploaded successfully');
        this.snackBar.openSuccessSnackBar(res.message);
        this.previewUrls = [];
        this.selectedFiles = [];
      },
      error: (error:HttpErrorResponse) => {
        this.snackBar.openFailedSnackBar(error);
      }
    })
  }

}
