export class User {
    fullName:string;
    email: string;
    mobileNo: string;

    constructor(fullName:string, email: string, mobileNo: string) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNo = mobileNo;
    }
}
