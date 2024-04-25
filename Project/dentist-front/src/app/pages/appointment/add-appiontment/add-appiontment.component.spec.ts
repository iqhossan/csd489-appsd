import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAppiontmentComponent } from './add-appiontment.component';

describe('AddAppiontmentComponent', () => {
  let component: AddAppiontmentComponent;
  let fixture: ComponentFixture<AddAppiontmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddAppiontmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddAppiontmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
