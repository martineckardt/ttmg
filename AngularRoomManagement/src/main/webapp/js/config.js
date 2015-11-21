/**
 * Created by felixb on 19/11/15.
 */

angular.module('ttmg').config(['$translateProvider', function ($translateProvider) {
    $translateProvider.useSanitizeValueStrategy('escapeParameters');
    $translateProvider.translations('en', {
        //General
        'APP_NAME': 'Time Table Management',
        'WELCOME_TEXT_HEADER': 'Welcome to the Time Table Management!',
        'WELCOME_TEXT_BODY': 'PLease add text here',

        'TIMETABLE': 'Time table',
        'SHOW': 'Show',
        'DOWNLOAD_PDF': 'Download PDF',
        'NAME': 'Name',
        'SAVE': 'Save',
        'BACK': 'Back',
        'ADD': 'Add',
        'DELETE': 'Delete',
        'QUESTION_DELETE_ITEM': 'Do you really want to delete the {{value}}',
        'CANCEL': 'Cancel',

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
        // === Courses ===
        // Course types
        'COURSE': 'Course',
        'SEMINAR': 'Seminar',
        'EXAM': 'Exam',
        'ELECTIVE_MODULE': "Elective module",

        // Properties
        'COURSE_TYPE': 'Course type',

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
        //General
        'APP_NAME': 'Time Table Management',
        'WELCOME_TEXT_HEADER': 'Willkommen zum Time Table Management System!',
        'WELCOME_TEXT_BODY': '@Basti: bitte noch einen kleinen Text über die Applikation einfügen.',

        'TIMETABLE': 'Stundenplan',
        'SHOW': 'Anzeigen',
        'DOWNLOAD_PDF': 'PDF Herunterladen',
        'NAME': 'Name',
        'SAVE': 'Speichern',
        'BACK': 'Zurück',
        'ADD': 'Hinzufügen',
        'DELETE': 'Löschen',
        'QUESTION_DELETE_ITEM': 'Möchten Sie {{value}} wirklich löschen?',
        'CANCEL': 'Abbrechen',

        //Centuria
        'CENTURIAS': 'Zenturien',
        'NBR_STUDENTS': 'Anzahl der Studenten',
        'PROGRAM': 'Studiengang',
        'SELECT_PROGRAM': 'Wählen Sie einen Studiengang',
        'YEAR': 'Jahrgang',
        'LETTER': 'Buchstabe',
        'CHANGE_TIME': 'Wechselzeit',
        'CREATE_CENTURIA': 'Neue Zenturie anlegen',
        'ADD_CENTURIA': 'Zenturie anlegen',
        'CENTURIA_CREATION_SUCCESS': 'Die Zenturie {{value}} wurde erfolgreich angelegt',
        'CENTURIA_CREATION_FAILURE': 'Die Zenturie konnte nicht angelegt werden',

        //TODO COURSES
        //Courses
        'COURSE': 'Kurs',
        'SEMINAR': 'Seminar',
        'EXAM': 'KLausur',
        'ELECTIVE_MODULE': "WPK",

        // Properties
        'COURSE_TYPE': 'Modulart',

        //Home
        'LOADING': 'Bitte warten...',

        //Room
        'ROOMS': 'Räume',
        'BUILDING': 'Gebäude',
        'SELECT_BUILDING': 'Gebäude auswählen',
        'ROOM_NBR': 'Raumnummer',
        'ROOM_CAPACITY': 'Kapazität',
        'ROOM_TYPE': 'Raumtyp',
        'SELECT_ROOM_TYPE': 'Raumtyp auswählen',
        'ROOM_TYPE_AUDIMAX': 'Audimax',
        'ROOM_TYPE_LECTURE': 'Hörsaal',
        'ROOM_TYPE_LAB': 'Labor',
        'ROOM_TYPE_COMPUTER': 'Computer-Raum',
        'CREATE_ROOM': 'Neuen Raum anlegen',
        'ADD_ROOM': 'Raum anlegen',
        'ROOM_CREATION_SUCCESS': 'Der Raum {{value}} wurde erfolgreich angelegt',
        'ROOM_CREATION_FAILURE': 'Der Raum konnte nicht angelegt werden',

        //Tutors
        'TUTORS': 'Dozenten',
        'TUTOR': 'Dozent',
        'TITLE': 'Titel',
        'FIRST_NAME': 'Vorname',
        'LAST_NAME': 'Nachname',
        'CREATE_TUTOR': 'Neuen Dozenten anlegen',
        'ADD_TUTOR': 'Dozenten anlegen',
        'TUTOR_CREATION_SUCCESS': 'Der Dozent{{value}} wurde erfolgreich angelegt',
        'TUTOR_CREATION_FAILURE': 'Der Dozent konnte nicht angelegt werden',

        //INDEX / MENU
        'SHOW_ROOMS': 'Räume anzeigen',
        'SHOW_TUTORS': 'Dozenten anzeigen',
        'SHOW_CENTURIAS': 'Zenturien anzeigen',
        'SHOW_COURSES': 'Kurse anzeigen',

        //ERRORS (See Constants.java)
        'DATE_RANGE_START_AFTER_END': 'Das End Datum muss nach dem Start Datum liegen.',
        'DATE_RANGE_ONE_NULL': 'Start- and End-Datum müssen eingetragen werden',
        'ENTITY_ALREADY_EXISTS': 'Die Entität ist schon im System und konnte nicht angelegt werden.',
        'ENTITY_NOT_FOUND': 'Entität des Typs {{type}} mit id {{id}} wurde nicht gefunden.',
        'INSUFFICIENT_SEATS': 'Es fehlen {{seats}} Sitzplätze.',
        'OBJECT_HAS_EVENTS': 'Kann {{type}} ´nicht löschen, es gibt noch {{numberEvents}} referenzierte Kurse!',
        'TIME_CONFLICTS': 'Es gibt {{number}} in Konflikt stehende Veranstaltungen.',
        'INVALID_PARAMETER': '"Der Parameter {{name}} {{description}} ist nicht valide.',

        //Invalid Parameter Exception
        'INVALID_FORMAT': 'hat ein invalides Format.',
        'INVALID_RANGE': 'hat einen Wert außerhalb der Grenzen.',
        'INVALID_LENGTH': 'hat ein invalides',
        'INVALID_NULL': 'darf nicht null sein',
        'INVALID_NOT_NULL': 'muss null sein',
        'INCONSISTENT': 'ist inkonsistent mit Objekt'
    });

    $translateProvider.preferredLanguage('de');
}]);
