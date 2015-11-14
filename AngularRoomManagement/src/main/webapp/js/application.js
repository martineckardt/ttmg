/**
 * The application.
 * Created by Stephan on 18.12.2014.
 */
'use strict';


var application = angular.module('ttmg', ['ngRoute', 'ttmg.provider', 'ttmg.controllers', 'ttmg.utils']);
var controllers = angular.module('ttmg.controllers', ['ttmg.provider']);
var services = angular.module('ttmg.provider', ['ngResource']);
var utils = angular.module('ttmg.utils', []);