import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BackendCabinasCabinaModule } from './cabina/cabina.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BackendCabinasCabinaModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendCabinasEntityModule {}
