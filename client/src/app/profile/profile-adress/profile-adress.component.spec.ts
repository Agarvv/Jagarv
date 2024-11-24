import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileAdressComponent } from './profile-adress.component';

describe('ProfileAdressComponent', () => {
  let component: ProfileAdressComponent;
  let fixture: ComponentFixture<ProfileAdressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProfileAdressComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileAdressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
