import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Cabina } from './cabina.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CabinaService {

    private resourceUrl = 'api/cabinas';

    constructor(private http: Http) { }

    create(cabina: Cabina): Observable<Cabina> {
        const copy = this.convert(cabina);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(cabina: Cabina): Observable<Cabina> {
        const copy = this.convert(cabina);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Cabina> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(cabina: Cabina): Cabina {
        const copy: Cabina = Object.assign({}, cabina);
        return copy;
    }
}
