import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BungalowSearchComponent } from './bungalow-search.component';

describe('BungalowSearchComponent', () => {
  let component: BungalowSearchComponent;
  let fixture: ComponentFixture<BungalowSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BungalowSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BungalowSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
