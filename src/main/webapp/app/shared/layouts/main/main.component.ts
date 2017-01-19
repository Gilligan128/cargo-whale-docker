import {Component, OnInit} from "@angular/core";
import {ApiService} from "./services/api.service";
import {MetricsService} from "./services/metrics.service";

@Component({
    selector: 'cw-main',
    templateUrl: 'app/shared/layouts/main/main.html'
})
export class MainComponent implements OnInit {

    private metrics: any;

    constructor(private apiService: ApiService, private metricsService: MetricsService) {
    }

    ngOnInit(): void {
        this.retrieveMetrics();
    }

    happyButton() {
        this.apiService.getHappy().subscribe(() => {
            this.retrieveMetrics();
        }, () => {
            this.retrieveMetrics();
        });
    }

    sadButton() {
        this.apiService.getSad().subscribe(() => {
            this.retrieveMetrics();
        }, () => {
            this.retrieveMetrics();
        });
    }

    madButton() {
        this.apiService.getMad().subscribe(() => {
            this.retrieveMetrics();
        }, () => {
            this.retrieveMetrics();
        });
    }

    private retrieveMetrics() {
        this.metricsService.getMetrics().subscribe(response => {
            console.log(response.json());
            this.metrics = response.json();
        }, error => {
            console.log(error.json());
        });
    }
}