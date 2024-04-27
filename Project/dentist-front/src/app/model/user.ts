export class User {
    id:number;
    username:String;
    password:String;
    firstname:String;
    lastname:String;
    email:String;
    roleId:number;
    roleName:String;
    authorities: Authority[];
}

export class Authority {
    authority: string;
}