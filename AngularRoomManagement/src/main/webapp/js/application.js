/**
 * The application.
 * Created by Martin Eckardt on 26.10.2015.
 */
'use strict';


var application = angular.module('ttmg', ['ngRoute', 'ttmg.provider', 'ttmg.controllers', 'ttmg.utils', 'pascalprecht.translate']);

// The controller module. Contains all controllers fpr the views
var controllers = angular.module('ttmg.controllers', ['ttmg.provider']);

// The provider module. Contains all entity factories
var provider = angular.module('ttmg.provider', ['ngResource']);

// The utils module. Contains some util diretives and all filters
var utils = angular.module('ttmg.utils', []);