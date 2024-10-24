export class Notification {

    constructor(

        public number: string,
        public title: string,
        public description: string,
        public status: string, // ENUM
        public dateCreated: Date,
        public dateClosed: Date,
        public authorId: number,
        public equipmentId: number,
        public locationId: number

    ){}

}
