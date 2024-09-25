import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewHistoryComponent } from './review-history.component';

describe('ReviewHistoryComponent', () => {
  let component: ReviewHistoryComponent;
  let fixture: ComponentFixture<ReviewHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReviewHistoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
