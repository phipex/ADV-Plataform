import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CabinaComponent } from './cabina.component';
import { CabinaDetailComponent } from './cabina-detail.component';
import { CabinaPopupComponent } from './cabina-dialog.component';
import { CabinaDeletePopupComponent } from './cabina-delete-dialog.component';

@Injectable()
export class CabinaResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const cabinaRoute: Routes = [
    {
        path: 'cabina',
        component: CabinaComponent,
        resolve: {
            'pagingParams': CabinaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendCabinasApp.cabina.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'cabina/:id',
        component: CabinaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendCabinasApp.cabina.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cabinaPopupRoute: Routes = [
    {
        path: 'cabina-new',
        component: CabinaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendCabinasApp.cabina.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cabina/:id/edit',
        component: CabinaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendCabinasApp.cabina.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cabina/:id/delete',
        component: CabinaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendCabinasApp.cabina.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
