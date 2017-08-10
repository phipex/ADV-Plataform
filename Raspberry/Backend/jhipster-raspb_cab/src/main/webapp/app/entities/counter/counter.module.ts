import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RaspbCabSharedModule } from '../../shared';
import {
    CounterService,
    CounterPopupService,
    CounterComponent,
    CounterDetailComponent,
    CounterDialogComponent,
    CounterPopupComponent,
    CounterDeletePopupComponent,
    CounterDeleteDialogComponent,
    counterRoute,
    counterPopupRoute,
    CounterResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...counterRoute,
    ...counterPopupRoute,
];

@NgModule({
    imports: [
        RaspbCabSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CounterComponent,
        CounterDetailComponent,
        CounterDialogComponent,
        CounterDeleteDialogComponent,
        CounterPopupComponent,
        CounterDeletePopupComponent,
    ],
    entryComponents: [
        CounterComponent,
        CounterDialogComponent,
        CounterPopupComponent,
        CounterDeleteDialogComponent,
        CounterDeletePopupComponent,
    ],
    providers: [
        CounterService,
        CounterPopupService,
        CounterResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RaspbCabCounterModule {}
