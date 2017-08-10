import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CounterComponent } from './counter.component';
import { CounterDetailComponent } from './counter-detail.component';
import { CounterPopupComponent } from './counter-dialog.component';
import { CounterDeletePopupComponent } from './counter-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class CounterResolvePagingParams implements Resolve<any> {

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

export const counterRoute: Routes = [
    {
        path: 'counter',
        component: CounterComponent,
        resolve: {
            'pagingParams': CounterResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raspbCabApp.counter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'counter/:id',
        component: CounterDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raspbCabApp.counter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const counterPopupRoute: Routes = [
    {
        path: 'counter-new',
        component: CounterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raspbCabApp.counter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'counter/:id/edit',
        component: CounterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raspbCabApp.counter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'counter/:id/delete',
        component: CounterDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raspbCabApp.counter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
