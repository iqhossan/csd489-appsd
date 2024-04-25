import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAppiontmentComponent } from './view-appiontment.component';

describe('ViewAppiontmentComponent', () => {
  let component: ViewAppiontmentComponent;
  let fixture: ComponentFixture<ViewAppiontmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewAppiontmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAppiontmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
