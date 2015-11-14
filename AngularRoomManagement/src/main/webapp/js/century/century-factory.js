/**
 * Created by Martin Eckardt on 28.10.2015.
 * Factory for querying a specific entitiy, all entities and creating a new entity of the type century.
 */

angular.module('ttmg.services').factory('CenturyFactory', function ($resource) {
    return $resource('rest/centurias/:id', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'}
    })
});
