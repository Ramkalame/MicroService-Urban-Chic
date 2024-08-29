import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerDocumentsComponent } from './seller-documents.component';

describe('SellerDocumentsComponent', () => {
  let component: SellerDocumentsComponent;
  let fixture: ComponentFixture<SellerDocumentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SellerDocumentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SellerDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
