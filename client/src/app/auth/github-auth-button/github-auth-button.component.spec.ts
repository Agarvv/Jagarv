import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GithubAuthButtonComponent } from './github-auth-button.component';

describe('GithubAuthButtonComponent', () => {
  let component: GithubAuthButtonComponent;
  let fixture: ComponentFixture<GithubAuthButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GithubAuthButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GithubAuthButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
