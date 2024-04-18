import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteSurgeryComponent } from './delete-surgery.component';

describe('DeleteSurgeryComponent', () => {
  let component: DeleteSurgeryComponent;
  let fixture: ComponentFixture<DeleteSurgeryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeleteSurgeryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleteSurgeryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
