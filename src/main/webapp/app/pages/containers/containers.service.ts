import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";

@Injectable()
export class ContainersService {

    constructor(private http: Http) {
    }

    getContainerIndex<T>(): Observable<T> {
        return this.http.get("/api/containers")
            .map(ContainersService.extractResponseBody)
            .catch(ContainersService.handleError);
    }

    getContainerDetails<T>(id: string): Observable<T> {
        return this.http.get("/api/containers/" + id)
            .map(ContainersService.extractResponseBody)
            .catch(ContainersService.handleError);
    }

    private static handleError(error: Response | any) {
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

    private static extractResponseBody(res: Response) {
        return res.json() || {};
    }
}
