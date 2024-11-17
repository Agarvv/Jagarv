import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPageAsideComponent } from './search-page-aside.component';

describe('SearchPageAsideComponent', () => {
  let component: SearchPageAsideComponent;
  let fixture: ComponentFixture<SearchPageAsideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SearchPageAsideComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchPageAsideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
