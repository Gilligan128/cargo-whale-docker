export class ContainerDetails {
    id: string;
    name: string;
    imageId: string;
    state: ContainerDetailsState;
    networkSettings: ContainerNetworkSettings;
    config: ContainerDetailsConfig;
}

export class ContainerDetailsState {
    status: string;
    pid: number;
    error: string;
    exitCode: number;
    startedTime: string;
    finishedTime: string;
    dead: boolean;
    paused: boolean;
    restarting: boolean;
    running: boolean;
}

export class ContainerNetworkSettings {
    ports: any;
    networks: any;
}

export class ContainerDetailsConfig {
    hostname: string;
    image: string;
    command: string[];
    environment: string[];
}