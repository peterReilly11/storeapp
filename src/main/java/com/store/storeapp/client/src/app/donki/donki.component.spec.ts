import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonkiComponent } from './donki.component';

describe('DonkiComponent', () => {
  let component: DonkiComponent;
  let fixture: ComponentFixture<DonkiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonkiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DonkiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
