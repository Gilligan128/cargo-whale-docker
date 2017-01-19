import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";

@Injectable()
export class MetricsService {

    constructor(private http: Http) {
    }

    getMetrics(): Observable<Response> {
        return this.http.get("/api/metrics");
    }
}