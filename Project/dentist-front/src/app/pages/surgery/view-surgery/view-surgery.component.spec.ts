import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSurgeryComponent } from './view-surgery.component';

describe('ViewSurgeryComponent', () => {
  let component: ViewSurgeryComponent;
  let fixture: ComponentFixture<ViewSurgeryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewSurgeryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewSurgeryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
