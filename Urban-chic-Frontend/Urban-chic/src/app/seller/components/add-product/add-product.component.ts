import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Form, FormArray, FormBuilder, FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';


type ProductAttribute = FormGroup<{
  attributeName: FormControl<string>,
  attributeValue: FormControl<string>
}>;

type VariantsAttribute = FormGroup<{
  variantAttributeName: FormControl<string>,
  variantAttributeValue: FormControl<string>,
}>;

type ProductVariants = FormGroup<{
  variantAttributes:FormArray<VariantsAttribute>,
  variantPrice: FormControl<number>,
  variantQuantity: FormControl<number>
}>;

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [MatIconModule, MatInputModule, MatFormFieldModule, MatSelectModule, MatDividerModule, MatButtonModule,
    ReactiveFormsModule, CommonModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  fb = inject(NonNullableFormBuilder);

  //product images
  previewUrls: string[] = [];  // Holds the preview URLs for all selected files
  selectedFiles: File[] = [];  // Holds the selected files

  productForm = this.fb.group({
      productName: new FormControl<string>(''),
      productDescription: new FormControl<string>(''),
      productBrand: new FormControl<string>(''),
      productCategory: new FormControl<string>(''),
      productSubcategory: new FormControl<string>(''),
      productType: new FormControl<string>(''),
      productAttributes: this.fb.array<ProductAttribute>([this.generateProductAttribute()]),
      productVariants: this.fb.array<ProductVariants>([this.generateProductVariants()])
  })

  
  constructor() {}


  generateProductAttribute():ProductAttribute{
    return this.fb.group({
      attributeName: '',
      attributeValue: ''
    })
  }

  generateProductVariants():ProductVariants{
    return this.fb.group({
      variantAttributes: this.fb.array<VariantsAttribute>([this.generateVariantsAttribute()]),
      variantPrice: new FormControl(),
      variantQuantity: new FormControl()
    })
  }

  generateVariantsAttribute(): VariantsAttribute{
    return this.fb.group({
      variantAttributeName: '',
      variantAttributeValue: ''
    })
  }

  addProductAttribute(){
    this.productForm.controls.productAttributes.push(this.generateProductAttribute())
  }

  removeProductAttribute(productAttributeIndex: number){
    this.productForm.controls.productAttributes.removeAt(productAttributeIndex);
  }

  addProductVariants(){
    this.productForm.controls.productVariants.push(this.generateProductVariants())
  }

  removeProductVariants(variantsIndex: number){
    this.productForm.controls.productVariants.removeAt(variantsIndex);
  }

  addVariantAttributes(variantIndex: number){
    this.productForm.controls.productVariants.at(variantIndex).controls.variantAttributes.push(this.generateVariantsAttribute())
  }

  removeVariantAttributes(variantIndex: number, variantAttributeIndex: number){
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

  submitProductForm(){
    console.log(this.productForm.value); // implement form submission logic here
  }

}
