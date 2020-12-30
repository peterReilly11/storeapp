import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyCartHistoryComponent } from './my-cart-history.component';

describe('MyCartHistoryComponent', () => {
  let component: MyCartHistoryComponent;
  let fixture: ComponentFixture<MyCartHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyCartHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyCartHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
