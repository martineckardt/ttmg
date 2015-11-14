/**
 * The application.
 * Created by Stephan on 18.12.2014.
 */
'use strict';


var application = angular.module('ttmg', ['ngRoute', 'ttmg.services', 'ttmg.controllers', 'ttmg.utils']);
var controllers = angular.module('ttmg.controllers', ['ttmg.services']);
var services = angular.module('ttmg.services', ['ngResource']);
var utils = angular.module('ttmg.utils', []);