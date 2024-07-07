import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtpmodelComponent } from './otpmodel.component';

describe('OtpmodelComponent', () => {
  let component: OtpmodelComponent;
  let fixture: ComponentFixture<OtpmodelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OtpmodelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OtpmodelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
