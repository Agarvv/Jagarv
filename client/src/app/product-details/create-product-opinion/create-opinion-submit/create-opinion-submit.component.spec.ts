import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOpinionSubmitComponent } from './create-opinion-submit.component';

describe('CreateOpinionSubmitComponent', () => {
  let component: CreateOpinionSubmitComponent;
  let fixture: ComponentFixture<CreateOpinionSubmitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateOpinionSubmitComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateOpinionSubmitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
