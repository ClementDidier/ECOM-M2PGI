import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BungalowListComponent } from './bungalow-list.component';

describe('BungalowListComponent', () => {
  let component: BungalowListComponent;
  let fixture: ComponentFixture<BungalowListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BungalowListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BungalowListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
