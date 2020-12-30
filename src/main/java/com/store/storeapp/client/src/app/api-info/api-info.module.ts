import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class ApiInfoModule {
  public static nasaAPIKey: any = ''; //fill in personal NASA API key
  public static oktaIssuer: any = ''; //fill in personal okta dev org url
  public static storeAppAngularClientID: any = ''; //fill in okta app client ID
}
