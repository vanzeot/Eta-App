export class ExibitionNotification {

    constructor(

        public number: string,
        public title: string,
        public description: string,
        public status: string, // ENUM
        public dateCreated: Date,
        public dateClosed: Date,
        public authorName: string,
        public equipmentName: string,
        public locationName: string

    ){}

}
