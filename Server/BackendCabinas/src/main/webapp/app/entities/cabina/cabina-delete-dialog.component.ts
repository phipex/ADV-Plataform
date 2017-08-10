import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Cabina } from './cabina.model';
import { CabinaPopupService } from './cabina-popup.service';
import { CabinaService } from './cabina.service';

@Component({
    selector: 'jhi-cabina-delete-dialog',
    templateUrl: './cabina-delete-dialog.component.html'
})
export class CabinaDeleteDialogComponent {

    cabina: Cabina;

    constructor(
        private cabinaService: CabinaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cabinaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'cabinaListModification',
                content: 'Deleted an cabina'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cabina-delete-popup',
    template: ''
})
export class CabinaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cabinaPopupService: CabinaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.cabinaPopupService
                .open(CabinaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
