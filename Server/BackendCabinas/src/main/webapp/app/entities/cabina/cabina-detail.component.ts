import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Cabina } from './cabina.model';
import { CabinaService } from './cabina.service';

@Component({
    selector: 'jhi-cabina-detail',
    templateUrl: './cabina-detail.component.html'
})
export class CabinaDetailComponent implements OnInit, OnDestroy {

    cabina: Cabina;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private cabinaService: CabinaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCabinas();
    }

    load(id) {
        this.cabinaService.find(id).subscribe((cabina) => {
            this.cabina = cabina;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCabinas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'cabinaListModification',
            (response) => this.load(this.cabina.id)
        );
    }
}
