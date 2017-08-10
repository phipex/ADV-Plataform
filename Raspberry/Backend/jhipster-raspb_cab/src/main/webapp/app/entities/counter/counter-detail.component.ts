import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Counter } from './counter.model';
import { CounterService } from './counter.service';

@Component({
    selector: 'jhi-counter-detail',
    templateUrl: './counter-detail.component.html'
})
export class CounterDetailComponent implements OnInit, OnDestroy {

    counter: Counter;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private counterService: CounterService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCounters();
    }

    load(id) {
        this.counterService.find(id).subscribe((counter) => {
            this.counter = counter;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCounters() {
        this.eventSubscriber = this.eventManager.subscribe(
            'counterListModification',
            (response) => this.load(this.counter.id)
        );
    }
}
