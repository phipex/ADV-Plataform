import { BaseEntity } from './../../shared';

const enum EstadoCabina {
    'ACTIVO',
    ' INACTIVO',
    ' BLOQUEADA'
}

export class Cabina implements BaseEntity {
    constructor(
        public id?: number,
        public idem?: string,
        public responsable?: string,
        public direccion?: string,
        public departamento?: string,
        public municipio?: string,
        public cupo?: number,
        public consegutivo?: number,
        public longitud?: number,
        public latitud?: number,
        public estado?: EstadoCabina,
        public observaciones?: string,
        public userId?: number,
    ) {
    }
}
