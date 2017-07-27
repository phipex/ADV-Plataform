import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BackendCabinasSharedModule } from '../../shared';
import { BackendCabinasAdminModule } from '../../admin/admin.module';
import {
    CabinaService,
    CabinaPopupService,
    CabinaComponent,
    CabinaDetailComponent,
    CabinaDialogComponent,
    CabinaPopupComponent,
    CabinaDeletePopupComponent,
    CabinaDeleteDialogComponent,
    cabinaRoute,
    cabinaPopupRoute,
    CabinaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...cabinaRoute,
    ...cabinaPopupRoute,
];

@NgModule({
    imports: [
        BackendCabinasSharedModule,
        BackendCabinasAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CabinaComponent,
        CabinaDetailComponent,
        CabinaDialogComponent,
        CabinaDeleteDialogComponent,
        CabinaPopupComponent,
        CabinaDeletePopupComponent,
    ],
    entryComponents: [
        CabinaComponent,
        CabinaDialogComponent,
        CabinaPopupComponent,
        CabinaDeleteDialogComponent,
        CabinaDeletePopupComponent,
    ],
    providers: [
        CabinaService,
        CabinaPopupService,
        CabinaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendCabinasCabinaModule {}
