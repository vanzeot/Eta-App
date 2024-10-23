export class ExibitionOrder {

    constructor(

        public number: string,
        public title: string,
        public description: string,
        public status: string, // ENUM
        public dateCreated: Date,
        public dateClosed: Date,
        public authorRegistration: string,
        public authorName: string,
        public notificationNumber: string,
        public notificationTitle: string

    ){}
}
