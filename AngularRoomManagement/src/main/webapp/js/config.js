/**
 * Created by felixb on 19/11/15.
 */

app.config(['$translateProvider', function ($translateProvider) {
    $translateProvider.translations('en', {
        //General
        'APP_NAME': 'Time Table Management',
        'TIMETABLE': 'Time Table',
        'SHOW': 'Show',
        'DOWNLOAD_PDF': 'Download PDF',
        'NAME': 'Name',
        'SAVE': 'Save',
        'BACK': 'Back',
        'ADD': 'Add',
        'DELETE': 'Delete',
        'QUESTION_DELETE_ITEM': 'Do you really want to delete the {{value}}',

        //Centuria
        'CENTURIAS': 'Centurias',
        'NBR_STUDENTS': 'Number of students',
        'PROGRAM': 'Study Program',
        'SELECT_PROGRAM': 'Select a study program',
        'YEAR': 'Year',
        'LETTER': 'Letter',
        'CHANGE_TIME': 'Change Time',
        'CREATE_CENTURIA': 'Create new centuria',
        'ADD_CENTURIA': 'Add centuria',
        'CENTURIA_CREATION_SUCCESS': 'The centuria {{value}} has been created successfully',
        'CENTURIA_CREATION_FAILURE': 'The centuria could not be created',

        //TODO COURSES
        //Courses


        //Home
        'LOADING': 'Please wait while the application is loading',

        //Room
        'ROOMS': 'Rooms',
        'BUILDING': 'building',
        'SELECT_BUILDING': 'Select a building',
        'ROOM_NBR': 'Room number',
        'ROOM_CAPACITY': 'Capacity',
        'ROOM_TYPE': 'Room type',
        'SELECT_ROOM_TYPE': 'Select a room type',
        'ROOM_TYPE_AUDIMAX': 'Audimax',
        'ROOM_TYPE_LECTURE': 'Lecture',
        'ROOM_TYPE_LAB': 'Lab',
        'ROOM_TYPE_COMPUTER': 'Computer',
        'CREATE_ROOM': 'Create new room',
        'ADD_ROOM': 'Add room',
        'ROOM_CREATION_SUCCESS': 'The room {{value}} has been created successfully',
        'ROOM_CREATION_FAILURE': 'The room could not be created',

        //Tutors
        'TUTORS': 'Tutors',
        'TUTOR': 'Tutor',
        'TITLE': 'Title',
        'FIRST_NAME': 'First name',
        'LAST_NAME': 'Last name',
        'CREATE_TUTOR': 'Create new tutor',
        'ADD_TUTOR': 'Add tutor',
        'TUTOR_CREATION_SUCCESS': 'The tutor{{value}} has been created successfully',
        'TUTOR_CREATION_FAILURE': 'The tutor could not be created',

        //INDEX / MENU
        'SHOW_ROOMS': 'Show Rooms',
        'SHOW_TUTORS': 'Show Tutors',
        'SHOW_CENTURIAS': 'Show Centurias',
        'SHOW_COURSES': 'Show Courses',

        //ERRORS (See Constants.java)
        'DATE_RANGE_START_AFTER_END': 'End Date has to be after the start date.',
        'DATE_RANGE_ONE_NULL': 'Start- and End-Date have to be set together.',
        'ENTITY_ALREADY_EXISTS': 'Entity does already exist and cannot be recreated.',
        'ENTITY_NOT_FOUND': 'Entity of type {{type}} with id {{id}} was not found.',
        'INSUFFICIENT_SEATS': 'There are {{seats}} seats missing.',
        'OBJECT_HAS_EVENTS': 'Cannot delete {{type}} there are still {{numberEvents}} referenced events!',
        'TIME_CONFLICTS': 'There are {{number}} conflicting events.',
        'INVALID_PARAMETER': '"The parameter {{name}} {{description}}',

        //Invalid Parameter Exception
        'INVALID_FORMAT': 'has an invalid format / type.',
        'INVALID_RANGE': 'has a value beyond its bounds.',
        'INVALID_LENGTH': 'has an invalid',
        'INVALID_NULL': 'must not be null',
        'INVALID_NOT_NULL': 'must be null',
        'INCONSISTENT': 'is inconsistent with object'
    });

    $translateProvider.translations('de', {
        //TODO SEBASTIAN
        'TITLE': 'Hallo',
        'FOO': 'Dies ist ein Absatz'
    });

    $translateProvider.preferredLanguage('en');
}]);
