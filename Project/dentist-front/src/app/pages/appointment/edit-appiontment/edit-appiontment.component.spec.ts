import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAppiontmentComponent } from './edit-appiontment.component';

describe('EditAppiontmentComponent', () => {
  let component: EditAppiontmentComponent;
  let fixture: ComponentFixture<EditAppiontmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditAppiontmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditAppiontmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
