import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDentistComponent } from './view-dentist.component';

describe('ViewDentistComponent', () => {
  let component: ViewDentistComponent;
  let fixture: ComponentFixture<ViewDentistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewDentistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewDentistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
