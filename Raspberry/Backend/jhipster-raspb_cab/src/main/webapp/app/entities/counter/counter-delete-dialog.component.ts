import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Counter } from './counter.model';
import { CounterPopupService } from './counter-popup.service';
import { CounterService } from './counter.service';

@Component({
    selector: 'jhi-counter-delete-dialog',
    templateUrl: './counter-delete-dialog.component.html'
})
export class CounterDeleteDialogComponent {

    counter: Counter;

    constructor(
        private counterService: CounterService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.counterService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'counterListModification',
                content: 'Deleted an counter'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-counter-delete-popup',
    template: ''
})
export class CounterDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private counterPopupService: CounterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.counterPopupService
                .open(CounterDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
