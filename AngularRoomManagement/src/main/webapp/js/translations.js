/**
 * Created by felixb on 19/11/15.
 */

angular.module('ttmg').config(['$translateProvider', function ($translateProvider) {
    // Set german as the preferred language
    $translateProvider.preferredLanguage('de');
    $translateProvider.useSanitizeValueStrategy('escapeParameters');

    // English translations
    $translateProvider.translations('en', {
        //General
        'APP_NAME': 'Time Table Management',
        'WELCOME_TEXT_HEADER': 'Welcome to the Time Table Management!',
        'WELCOME_TEXT_BODY': 'Time Table Management is an application that helps you creating Time Tables.',
        'GERMAN': 'German',
        'ENGLISH': 'English',

        'TIMETABLE': 'Time table',
        'ACTIONS': 'Actions',
        'SHOW': 'Show',
        'DOWNLOAD_PDF': 'Download PDF',
        'NAME': 'Name',
        'SAVE': 'Save',
        'BACK': 'Back',
        'ADD': 'Add',
        'DELETE': 'Delete',
        'QUESTION_DELETE_ITEM': 'Do you really want to delete the {{value}}',
        'CANCEL': 'Cancel',
        'SUCCESS': 'Success',
        'NEXT': 'Next',

        // Properties
        'CHANGE_TIME': 'Change time',
        'CHANGE_TIME_OPTIONAL': 'Change Time (optional)',
        'MINUTES': 'minutes',

        //Centuria
        'CENTURIAS': 'Centurias',
        'CENTURIA': 'Centuria',
        'NBR_STUDENTS': 'Number of students',
        'PROGRAM': 'Study Program',
        'SELECT_PROGRAM': 'Select a study program',
        'YEAR': 'Year',
        'LETTER': 'Letter',
        'CREATE_NEW_CENTURIA': 'Create new centuria',
        'ADD_CENTURIA': 'Add centuria',
        'CENTURIA_CREATION_SUCCESS': 'The centuria {{value}} has been created successfully',
        'CENTURIA_CREATION_FAILURE': 'The centuria could not be created',

        //Event
        'CREATE_NEW_EVENT_FOR_COURSE': 'Create new events for the course ',
        'EDIT_EVENT_OF_COURSE': 'Edit the event of the course',
        'CREATE_EVENT': 'Create event',

        'EVENT': 'Event',
        'CAUSER': 'Causer',

        // create course events
        'DATE': "Date",
        'BEGIN': 'Begin',
        'END': 'End',
        'REPEAT_FOR_WEEKS': 'Repeat for weeks',


        //TODO COURSES
        // === Courses ===
        // Course types
        'COURSE': 'Course',
        'COURSES': 'Courses',
        'SEMINAR': 'Seminar',
        'EXAM': 'Exam',
        'ELECTIVE_MODULE': 'Elective module',

        // Course Properties
        'COURSE_TYPE': 'Course type',
        'COURSE_NAME': 'Name',
        'SELECT_COURSE_TYPE': 'Select a course type',
        'PARTICIPANTS_OPTIONAL': 'Participants (optional)',
        'SEMINARS_NO_PARTICIPANTS': 'Seminars do not have participants.',
        'PARTICIPANTS': 'Participants',

        // Actions
        'ADD_EVENTS': 'Add events',
        'EDIT_COURSE': 'Edit course',
        'CREATE_NEW_COURSE': 'Create a new course',
        'ADD_COURSE': 'Add Course',

        //Home
        'LOADING': 'Please wait while the application is loading',

        //Room
        'ROOMS': 'Rooms',
        'ROOM': 'Room',
        'BUILDING': 'building',
        'SELECT_BUILDING': 'Select a building',
        'ROOM_NBR': 'Room number',
        'ROOM_CAPACITY': 'Capacity',
        'ROOM_TYPE': 'Room type',
        'SELECT_ROOM_TYPE': 'Select a room type',
        'CREATE_NEW_ROOM': 'Create new room',
        'ADD_ROOM': 'Add room',
        'ROOM_CREATION_SUCCESS': 'The room {{value}} has been created successfully',
        'ROOM_CREATION_FAILURE': 'The room could not be created',
        'AVAILABLE_ROOMS': 'Available rooms',
        'NO_SUFF_LARGE_ROOM': 'No sufficiently large rooms are available',
        'FILTER_ROOMS': 'Filter rooms',

        // Messages
        'ROOM_CREATED': 'The room was successfully created:',

        // Properties
        'AUDIMAX': 'Audimax',
        'LECTURE': 'Lecture',
        'LAB': 'Lab',
        'COMPUTER': 'Computer room',

        //Tutors
        'TUTORS': 'Tutors',
        'TUTOR': 'Tutor',
        'TITLE': 'Title',
        'FIRST_NAME': 'First name',
        'LAST_NAME': 'Last name',
        'CREATE_NEW_TUTOR': 'Create a new tutor',
        'ADD_TUTOR': 'Add tutor',

        // Messages
        'TUTOR_CREATED': 'The tutor has been created successfully:',

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
        'INVALID_PARAMETER': 'The parameter {{name}} {{description}}',

        //Invalid Parameter Exception
        'INVALID_FORMAT': 'has an invalid format / type.',
        'INVALID_RANGE': 'has a value beyond its bounds.',
        'INVALID_LENGTH': 'has an invalid',
        'INVALID_NULL': 'must not be null',
        'INVALID_NOT_NULL': 'must be null',
        'INCONSISTENT': 'is inconsistent with object'
    });

    // German translations
    $translateProvider.translations('de', {
        // === General ===
        'APP_NAME': 'Time Table Management',
        'WELCOME_TEXT_HEADER': 'Willkommen zum Time Table Management System!',
        'WELCOME_TEXT_BODY': 'Mit dem Time Table Management System können Sie Räume, Dozenten und Zenturien zu den Stammdaten hinzufügen und Veranstaltungen verwalten.',
        'GERMAN': 'Deutsch',
        'ENGLISH': 'Englisch',

        'TIMETABLE': 'Stundenplan',
        'ACTIONS': 'Aktionen',
        'SHOW': 'Anzeigen',
        'DOWNLOAD_PDF': 'PDF Herunterladen',
        'NAME': 'Name',
        'SAVE': 'Speichern',
        'BACK': 'Zurück',
        'ADD': 'Hinzufügen',
        'DELETE': 'Löschen',
        'QUESTION_DELETE_ITEM': 'Möchten Sie {{value}} wirklich löschen?',
        'CANCEL': 'Abbrechen',
        'SUCCESS': 'Erfolg',
        'NEXT': 'Weiter',

        // Properties
        'CHANGE_TIME': 'Wechselzeit',
        'CHANGE_TIME_OPTIONAL': 'Wechselzeit (optional)',
        'MINUTES': 'Minuten',

        //Centuria
        'CENTURIAS': 'Zenturien',
        'CENTURIA': 'Zenturie',
        'NBR_STUDENTS': 'Anzahl der Studenten',
        'PROGRAM': 'Studiengang',
        'SELECT_PROGRAM': 'Wählen Sie einen Studiengang',
        'YEAR': 'Jahrgang',
        'LETTER': 'Buchstabe',
        'CREATE_NEW_CENTURIA': 'Neue Zenturie anlegen',
        'ADD_CENTURIA': 'Zenturie anlegen',
        'CENTURIA_CREATION_SUCCESS': 'Die Zenturie {{value}} wurde erfolgreich angelegt',
        'CENTURIA_CREATION_FAILURE': 'Die Zenturie konnte nicht angelegt werden',

        // === Events ===
        'CREATE_NEW_EVENT_FOR_COURSE': 'Erstelle neue Veranstaltungen für den Kurs ',
        'EDIT_EVENT_OF_COURSE': 'Bearbeiten der Veranstaltung des Kurses',
        'CREATE_EVENT': 'Erstelle neue Veranstaltung',

        'EVENT': 'Veranstaltung',
        'CAUSER': 'Causer',

        // create course events
        'DATE': "Datum",
        'BEGIN': 'Beginn',
        'END': 'Ende',
        'REPEAT_FOR_WEEKS': 'Wöchentlich wiederholen',

        //TODO COURSES
        // === Courses ===
        'COURSE': 'Kurs',
        'COURSES': 'Kurse',
        'SEMINAR': 'Seminar',
        'EXAM': 'Klausur',
        'ELECTIVE_MODULE': 'WPK',

        // Course Properties
        'COURSE_TYPE': 'Modulart',
        'COURSE_NAME': 'Name',
        'SELECT_COURSE_TYPE': 'Wählen Sie einen Kurs-Typ',
        'PARTICIPANTS_OPTIONAL': 'Teilnehmer (optional)',
        'SEMINARS_NO_PARTICIPANTS': 'Seminare haben keine Teilnehmer.',
        'PARTICIPANTS': 'Teilnehmer',
        // Actions
        'ADD_EVENTS': 'Veranstaltungen hinzufügen',
        'EDIT_COURSE': 'Kurs verändern',
        'CREATE_NEW_COURSE': 'Neuen Kurs anlegen',
        'ADD_COURSE': 'Kurs anlegen',

        //Home
        'LOADING': 'Bitte warten...',

        // === Room ===
        'ROOMS': 'Räume',
        'ROOM': 'Raum',
        'BUILDING': 'Gebäude',
        'SELECT_BUILDING': 'Gebäude auswählen',
        'ROOM_NBR': 'Raumnummer',
        'ROOM_CAPACITY': 'Kapazität',
        'ROOM_TYPE': 'Raumtyp',
        'SELECT_ROOM_TYPE': 'Raumtyp auswählen',
        'CREATE_NEW_ROOM': 'Neuen Raum anlegen',
        'ADD_ROOM': 'Raum anlegen',
        'ROOM_CREATION_SUCCESS': 'Der Raum {{value}} wurde erfolgreich angelegt',
        'ROOM_CREATION_FAILURE': 'Der Raum konnte nicht angelegt werden',
        'AVAILABLE_ROOMS': 'Verfügbare Räume',
        'NO_SUFF_LARGE_ROOM': 'Kein ausreichend großer Raum steht zur Verfügung',
        'FILTER_ROOMS': 'Räume filtern',

        // Room type
        'AUDIMAX': 'Audimax',
        'LECTURE': 'Hörsaal',
        'LAB': 'Labor',
        'COMPUTER': 'Computer-Raum',

        // Messages
        'ROOM_CREATED': 'Der Raum wurde erfolgreich erstellt:',

        //Tutors
        'TUTORS': 'Dozenten',
        'TUTOR': 'Dozent',
        'TITLE': 'Titel',
        'FIRST_NAME': 'Vorname',
        'LAST_NAME': 'Nachname',
        'CREATE_NEW_TUTOR': 'Neuen Dozenten anlegen',
        'ADD_TUTOR': 'Dozent anlegen',
        'TUTOR_CREATION_SUCCESS': 'Der Dozent wurde erfolgreich angelegt',
        'TUTOR_CREATION_FAILURE': 'Der Dozent konnte nicht angelegt werden',

        // Messages
        'TUTOR_CREATED': 'Der Tutor wurde erfolgreich erstellt:',

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
        'INVALID_PARAMETER': 'Der Parameter {{name}} {{description}} ist nicht valide.',

        //Invalid Parameter Exception
        'INVALID_FORMAT': 'hat ein invalides Format.',
        'INVALID_RANGE': 'hat einen Wert außerhalb der Grenzen.',
        'INVALID_LENGTH': 'hat ein invalides',
        'INVALID_NULL': 'darf nicht null sein',
        'INVALID_NOT_NULL': 'muss null sein',
        'INCONSISTENT': 'ist inkonsistent mit Objekt'
    });
}]);
