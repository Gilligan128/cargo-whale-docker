import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";

@Injectable()
export class ApiService {

    constructor(private http: Http) {
    }

    getHappy(): Observable<Response> {
        return this.http.get("/api/happy");
    }

    getSad(): Observable<Response> {
        return this.http.get("/api/sad");
    }

    getMad(): Observable<Response> {
        return this.http.get("/api/mad");
    }
}