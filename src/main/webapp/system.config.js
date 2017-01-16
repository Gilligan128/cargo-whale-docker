/**
 * System configuration for Angular 2 app
 * Adjust as necessary for your application needs.
 */
(function (global) {
    // default js extension set true
    System.defaultJsExtension = true;
    // map tells the System loader where to look for things
    var map = {
        'app': 'app', // 'dist',
        '@angular': 'vendor/@angular',
        'rxjs': 'vendor/rxjs',
        'main': 'app.main',
        '@ng-bootstrap': 'vendor/@ng-bootstrap',
        'jquery': 'vendor/jquery/dist',
        'ng2-webstorage': 'vendor/ng2-webstorage',
        // app barrels
        'containers': 'app/pages/containers',
        'pages': 'app/pages',
        'layouts': 'app/shared/layouts',
        'pipes': 'app/shared/pipes'
    };
    // packages tells the System loader how to load when no filename and/or no extension
    var packages = {
        'app': {main: 'app.main'},
        'rxjs': {},
        '@ng-bootstrap/ng-bootstrap': {main: '/bundles/ng-bootstrap', defaultExtension: 'js'},
        'ui-router-ng2': {},
        'jquery': {main: 'jquery.min', defaultExtension: 'js'},
        'ng2-webstorage': {main: 'bundles/core.umd.js', defaultExtension: 'js'},
        // app barrels
        'containers': {main: 'index', defaultExtension: 'js'},
        'pages': {main: 'index', defaultExtension: 'js'},
        'layouts': {main: 'index', defaultExtension: 'js'},
        'pipes': {main: 'index', defaultExtension: 'js'}
    };
    var ngPackageNames = [
        'common',
        'compiler',
        'core',
        'forms',
        'http',
        'platform-browser',
        'platform-browser-dynamic',
        'router'
    ];

    // Individual files (~300 requests):
    function packIndex(pkgName) {
        packages['@angular/' + pkgName] = {main: 'index'};
    }

    // Bundled (~40 requests):
    function packUmd(pkgName) {
        packages['@angular/' + pkgName] = {main: '/bundles/' + pkgName + '.umd'};
    }

    // Most environments should use UMD; some (Karma) need the individual index files
    var setPackageConfig = System.packageWithIndex ? packIndex : packUmd;
    // Add package entries for angular packages
    ngPackageNames.forEach(setPackageConfig);

    var config = {
        map: map,
        packages: packages
    };

    // Allow imports from "angular" even though it's loaded as a <script>
    // This is similar to webpack `externals: []`
    // System.set('angular', System.newModule(window.angular));
    System.config(config);
})(this);
