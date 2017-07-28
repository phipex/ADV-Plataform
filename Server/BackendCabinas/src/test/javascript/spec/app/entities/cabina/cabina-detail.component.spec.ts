/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BackendCabinasTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CabinaDetailComponent } from '../../../../../../main/webapp/app/entities/cabina/cabina-detail.component';
import { CabinaService } from '../../../../../../main/webapp/app/entities/cabina/cabina.service';
import { Cabina } from '../../../../../../main/webapp/app/entities/cabina/cabina.model';

describe('Component Tests', () => {

    describe('Cabina Management Detail Component', () => {
        let comp: CabinaDetailComponent;
        let fixture: ComponentFixture<CabinaDetailComponent>;
        let service: CabinaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendCabinasTestModule],
                declarations: [CabinaDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CabinaService,
                    JhiEventManager
                ]
            }).overrideTemplate(CabinaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CabinaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CabinaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Cabina(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.cabina).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
