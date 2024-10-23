export class Confirmation {

    constructor(

        public number: string,
        public type: string, // ENUM
        public description: string,
        public dateStarted: Date,
        public dateFinished: Date,
        public executorId: number,
        public orderId: number

    ){}

}
