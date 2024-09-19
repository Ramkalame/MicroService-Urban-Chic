import { CommonModule } from '@angular/common';
import {
  AfterViewInit,
  ChangeDetectionStrategy,
  Component,
  inject,
  OnInit,
  signal,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { AddProductComponent } from "../add-product/add-product.component";
import { Product } from '../../../common/models/product-models/product-response.model';
import { ProductService } from '../../../common/services/product.service';
import { Page } from '../../../core/models/page-model.model';
import { ApiResponse } from '../../../core/models/shared-models/api-response.model';
import { SellerAuthService } from '../../../core/services/seller-auth.service';


@Component({
  selector: 'app-product-management',
  standalone: true,
  imports: [
    MatGridListModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatSelectModule, MatDialogModule,
    MatDividerModule,
    AddProductComponent
],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './product-management.component.html',
  styleUrl: './product-management.component.css',
})
export class ProductManagementComponent implements AfterViewInit, OnInit {

  //add-product component
  @ViewChild('addProductComponent') addProductComponent!:AddProductComponent;

  //dialogs
  @ViewChild('productDialogTemplate') productDialogTemplate!: TemplateRef<any>;
  productDialogRef!: MatDialogRef<any>;
  @ViewChild('productUpdateDialogTemplate') productUpdateDialogTemplate!: TemplateRef<any>;
  productUpdateDialogRef!: MatDialogRef<any>;
  @ViewChild('productDeleteDialogTemplate') productDeleteDialogTemplate!: TemplateRef<any>;
  productDeleteDialogRef!: MatDialogRef<any>;


  //paginatr and sorting 
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  //service injection 
  productService = inject(ProductService);
  sellerAuthService = inject(SellerAuthService);

  displayedColumns: string[] = ['productId', 'productName', 'productCategory', 'totalStock', 'rating', 'status', 'actions'];
  dataSource = new MatTableDataSource<Product>();
  viewProduct!:Product;
  userId!: string;

  constructor(private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userId = this.sellerAuthService.getUserId();
    this.getProductDetails(this.userId,0,10);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    //listen to paginator events 
    this.paginator.page.subscribe(() => {
      this.getProductDetails(this.userId,this.paginator.pageIndex,this.paginator.pageSize);
    });
  }


  //modals 
  openProductDialog(productId: string) {
    this.productDialogRef = this.dialog.open(this.productDialogTemplate, {
      data: productId,
      disableClose: true,
      width: '50vw',       
      maxWidth: '50vw', 
    });
    this.viewProduct = this.dataSource.data.find(data => data.productId === productId) as Product;
  }

  closeProductDialog(): void {
    this.productDialogRef.close();
  }

  openProductUpdateDialog(productId?: string) {
    this.productUpdateDialogRef = this.dialog.open(this.productUpdateDialogTemplate, {
      data: productId,
      disableClose: true,
      width: '80vw',       
      maxWidth: '100vw',  
    });
  }

  closeProductUpdateDialog(): void {
    this.productUpdateDialogRef.close();
  }

  openProductDeleteDialog(productId?: string) {
    this.productDeleteDialogRef = this.dialog.open(this.productDeleteDialogTemplate, {
      data: productId,
      disableClose: true,
      width: '400px'
    });
  }

  closeProductDeleteDialog(): void {
    this.productDeleteDialogRef.close();
  }

  //others

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  //api methods 
  getProductDetails(sellerId: string,page: number,size: number){
    this.productService.getAllProductsBySeller(sellerId,page,size).subscribe({
      next: (res:ApiResponse<Page<Product>>) => {
        this.dataSource.data = res.data.content;
        console.log('res.data.content ---> ',res.data.content);
        console.log('datasource',this.dataSource.filteredData);
        
      },
      error: (error) => {
        console.error('Error fetching product details', error);
      }
    })
    
  }


}

