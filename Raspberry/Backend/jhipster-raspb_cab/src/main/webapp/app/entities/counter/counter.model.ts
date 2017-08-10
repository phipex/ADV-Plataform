import { BaseEntity } from './../../shared';

export class Counter implements BaseEntity {
    constructor(
        public id?: number,
        public registry?: string,
        public counter?: number,
        public total?: number,
    ) {
    }
}
