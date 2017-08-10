import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Counter } from './counter.model';
import { CounterPopupService } from './counter-popup.service';
import { CounterService } from './counter.service';

@Component({
    selector: 'jhi-counter-dialog',
    templateUrl: './counter-dialog.component.html'
})
export class CounterDialogComponent implements OnInit {

    counter: Counter;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private counterService: CounterService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.counter.id !== undefined) {
            this.subscribeToSaveResponse(
                this.counterService.update(this.counter));
        } else {
            this.subscribeToSaveResponse(
                this.counterService.create(this.counter));
        }
    }

    private subscribeToSaveResponse(result: Observable<Counter>) {
        result.subscribe((res: Counter) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Counter) {
        this.eventManager.broadcast({ name: 'counterListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-counter-popup',
    template: ''
})
export class CounterPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private counterPopupService: CounterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.counterPopupService
                    .open(CounterDialogComponent, params['id']);
            } else {
                this.modalRef = this.counterPopupService
                    .open(CounterDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
