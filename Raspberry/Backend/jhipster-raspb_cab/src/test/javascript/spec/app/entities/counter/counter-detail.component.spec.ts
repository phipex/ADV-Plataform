/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RaspbCabTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CounterDetailComponent } from '../../../../../../main/webapp/app/entities/counter/counter-detail.component';
import { CounterService } from '../../../../../../main/webapp/app/entities/counter/counter.service';
import { Counter } from '../../../../../../main/webapp/app/entities/counter/counter.model';

describe('Component Tests', () => {

    describe('Counter Management Detail Component', () => {
        let comp: CounterDetailComponent;
        let fixture: ComponentFixture<CounterDetailComponent>;
        let service: CounterService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RaspbCabTestModule],
                declarations: [CounterDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CounterService,
                    JhiEventManager
                ]
            }).overrideTemplate(CounterDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CounterDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CounterService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Counter(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.counter).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
