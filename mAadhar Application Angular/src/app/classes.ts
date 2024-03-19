export class Citizen {
    id!: number;
    name!: string;
    dob!: Date;
    address!: string;
    emailId!: string;
    mobileNo!: string;
    gender!: string;
    isLoggedIn!: boolean;
    IsAlive!: boolean;
    aadharCard?: AadharCard;
    status!: string;
}

export class AadharCard {
    id!: string;
    issueDate!: Date;
    status!: string;
}

export class AdminUser {
    id!: number;
    adminName!: string;
    email!: string;
    password!: string;
    isLoggedIn!: boolean;
}