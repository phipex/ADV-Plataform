import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { RaspbCabCounterModule } from './counter/counter.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        RaspbCabCounterModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RaspbCabEntityModule {}
