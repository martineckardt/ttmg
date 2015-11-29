/**
 * Created by felixb on 19/11/15.
 */

angular.module('ttmg').config(['$translateProvider', function ($translateProvider) {
    // Set german as the preferred language
    $translateProvider.preferredLanguage('de');
    $translateProvider.useSanitizeValueStrategy('escapeParameters');

    // English translations
    $translateProvider.translations('en', {
        // === General ===
        'APP_NAME': 'Time Table Management',
        'WELCOME_TEXT_HEADER': 'Welcome to the Time Table Management!',
        'WELCOME_TEXT_BODY': 'Time Table Management is an application that helps you creating Time Tables.',

        // Language
        'GERMAN': 'German',
        'ENGLISH': 'English',

        // COMMON

        // Actions
        'ACTIONS': 'Actions',
        'SHOW': 'Show',
        'DOWNLOAD_PDF': 'Download PDF',
        'SAVE': 'Save',
        'BACK': 'Back',
        'ADD': 'Add',
        'DELETE': 'Delete',
        'EDIT': 'Edit',
        'CANCEL': 'Cancel',

        'NEXT': 'Next',

        // Properties
        'TIMETABLE': 'Time table',
        'NAME': 'Name',
        'CHANGE_TIME': 'Change time',
        'CHANGE_TIME_OPTIONAL': 'Change Time in minutes (optional)',
        'MINUTES': 'minutes',

        // Messages
        'SUCCESS': 'Success',
        'QUESTION_DELETE_ITEM': 'Do you really want to delete the {{value}}',
        'FILL_REQUIRED_FIELDS': 'Please fill the required fields.',

        // Error Messages
        'ERROR_REQUIRED': 'Please fill the required field.',
        'ERROR_OUT_OF_BOUNDS': 'PLease enter an integer between {{min]} and {{max}}.',
        'ERROR_PATTERN_DEFAULT': 'Only the following characters are permitted: "A-Z", "a-z", ".", Special characters:: "ß, ä, ö, ü", "-", SPACE',
        'ERROR_PATTERN_LETTER': 'PLease enter only one character from a to z.',
        'ERROR_PATTERN_COURSE': 'Only the following characters are permitted: "A-Z", "a-z", ".", Special characters: "ß, ä, ö, ü", "-", SPACE. ' +
        'The course name cannot start with a "." or a SPACE.',
        'ERROR_PATTERN_NAME': 'Only the following characters are permitted: "A-Z", "a-z", ".", Special characters: "ß, ä, ö, ü", "-", SPACE. ' +
        'The value has to start with an capital letter.',
        'ERROR_MAXLENGTH': 'Please do not enter more than 50 characters.',

        // === Centuria ===

        'CENTURIAS': 'Centurias',
        'CENTURIA': 'Centuria',

        // Properties
        'NBR_STUDENTS': 'Number of students',
        'PROGRAM': 'Study Program',
        'SELECT_PROGRAM': 'Select a study program',
        'YEAR': 'Year',
        'LETTER': 'Letter',

        // Actions
        'CREATE_NEW_CENTURIA': 'Create new centuria',
        'ADD_CENTURIA': 'Add centuria',

        // Messages
        'CENTURIA_CREATION_SUCCESS': 'The centuria {{value}} has been created successfully',
        'CENTURIA_CREATION_FAILURE': 'The centuria could not be created',

        // === Event ===
        'EVENT': 'Event',
        'CAUSER': 'Causer',

        // Properties
        'DATE': "Date",
        'BEGIN': 'Begin',
        'END': 'End',
        'REPEAT_FOR_WEEKS': 'Repeat for weeks',

        // Actions
        'CREATE_NEW_EVENT_FOR_COURSE': 'Create new events for the course ',
        'EDIT_EVENT_OF_COURSE': 'Edit the event of the course',
        'CREATE_EVENT': 'Create event',
        'FORCE_CREATE': 'Create anyway',
        'FORCE_UPDATE': 'Update anyway',

        // Messages
        'COURSE_EVENTS_CREATION_SUCCESS': 'The events have been created successfully.',
        'COURSE_EVENTS_CREATION_FAILURE': 'An eror occured creating the events.',
        'EVENT_UPDATE_SUCCESS': 'The event has been updated successfully.',
        'EVENT_UPDATE_FAILURE': 'An error occured updating the event:',


        // === Courses ===

        // Course types
        'COURSE': 'Course',
        'COURSES': 'Courses',
        'SEMINAR': 'Seminar',
        'EXAM': 'Exam',
        'ELECTIVE_MODULE': 'Elective module',

        // Properties
        'COURSE_TYPE': 'Course type',
        'COURSE_NAME': 'Name',
        'SELECT_COURSE_TYPE': 'Select a course type',
        'PARTICIPANTS_OPTIONAL': 'Participants (optional)',
        'PARTICIPANTS': 'Participants',
        'SEMINARS_NO_PARTICIPANTS': 'Seminars do not have participants.',

        // Actions
        'ADD_EVENTS': 'Add events',
        'EDIT_COURSE': 'Edit course',
        'CREATE_NEW_COURSE': 'Create a new course',
        'ADD_COURSE': 'Add Course',
        'BACK_TO_COURSE': 'Zurück zum Kurs',

        // Messages
        'TUTOR_REQUIRED': 'Please create at least one tutor before creating an course.',
        'COURSE_CREATION_SUCCESS': 'The course has been created successfully:',
        'COURSE_CREATION_FAILURE': 'The course could not be created:',
        'COURSE_UPDATE_SUCCESS': 'The course has been updated successfully.',
        'COURSE_UPDATE_FAILURE': 'An error occured updating the course',

        // === Room ===
        'ROOMS': 'Rooms',
        'ROOM': 'Room',
        'AVAILABLE_ROOMS': 'Available rooms',
        'FILTER_ROOMS': 'Filter rooms',

        // Room types
        'AUDIMAX': 'Audimax',
        'LECTURE': 'Lecture',
        'LAB': 'Lab',
        'COMPUTER': 'Computer room',

        // Properties
        'BUILDING': 'building',
        'SELECT_BUILDING': 'Select a building',
        'ROOM_NBR': 'Room number',
        'ROOM_CAPACITY': 'Capacity',
        'ROOM_TYPE': 'Room type',
        'SELECT_ROOM_TYPE': 'Select a room type',

        // Actions
        'CREATE_NEW_ROOM': 'Create new room',
        'ADD_ROOM': 'Add room',

        // Messages
        'ROOM_CREATION_SUCCESS': 'The room {{value}} has been created successfully',
        'ROOM_CREATION_FAILURE': 'The room could not be created',
        'NO_SUFF_LARGE_ROOM': 'No sufficiently large rooms are available',
        'ROOM_CREATED': 'The room was successfully created:',

        // === Tutor ===

        'TUTORS': 'Tutors',
        'TUTOR': 'Tutor',

        // Properties
        'TITLE': 'Title',
        'FIRST_NAME': 'First name',
        'LAST_NAME': 'Last name',

        // Actions
        'CREATE_NEW_TUTOR': 'Create a new tutor',
        'ADD_TUTOR': 'Add tutor',

        // Messages
        'TUTOR_CREATED': ':',
        'TUTOR_CREATION_SUCCESS': 'The tutor has been created successfully:',
        'TUTOR_CREATION_FAILURE': 'An error occured creating the tutor:',

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
        'INSUFFICIENT_SEATS': 'There is not enough capacity in the rooms of the events for all participants of the ' +
        'course. Please note that if you change the course anyway, that additional time conflicts might appear.',
        'OBJECT_HAS_EVENTS': 'Cannot delete {{type}} there are still {{numberEvents}} referenced events!',
        'TIME_CONFLICTS': 'There are {{nbrOfConflicts}} conflicting events.',
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

        // LANGUAGE
        'GERMAN': 'Deutsch',
        'ENGLISH': 'Englisch',

        // === COMMON ===

        // Actions
        'ACTIONS': 'Aktionen',
        'SHOW': 'Anzeigen',
        'DOWNLOAD_PDF': 'PDF Herunterladen',
        'SAVE': 'Speichern',
        'BACK': 'Zurück',
        'ADD': 'Hinzufügen',
        'DELETE': 'Löschen',
        'EDIT': 'Bearbeiten',
        'CANCEL': 'Abbrechen',
        'NEXT': 'Weiter',

        // Properties
        'TIMETABLE': 'Stundenplan',
        'NAME': 'Name',
        'CHANGE_TIME': 'Wechselzeit',
        'CHANGE_TIME_OPTIONAL': 'Wechselzeit in Minuten (optional)',
        'MINUTES': 'Minuten',

        // Messages
        'SUCCESS': 'Erfolg',
        'QUESTION_DELETE_ITEM': 'Möchten Sie {{value}} wirklich löschen?',

        // Error Messages
        'ERROR_REQUIRED': 'Bitte füllen Sie das Pflichtfeld aus.',
        'ERROR_OUT_OF_BOUNDS': 'Bitte tragen Sie eine ganze Zahl zwischen {{min}} und {{max}} ein.',
        'ERROR_PATTERN_DEFAULT': 'Es sind nur folgende Zeichen erlaubt: "A-Z", "a-z", ".", Sonderzeichen: "ß, ä, ö, ü", "-", SPACE',
        'ERROR_PATTERN_LETTER': 'Bitte tragen Sie nur einen Buchstaben von a bis z ein.',
        'ERROR_PATTERN_COURSE': 'Es sind nur folgende Zeichen erlaubt: "A-Z", "a-z", ".", Sonderzeichen: "ß, ä, ö, ü", "-", SPACE. ' +
        'Der Kurs-Name darf nicht mit einem Punkt oder einen Leerzeichen beginnen.',
        'ERROR_PATTERN_NAME': 'Es sind nur folgende Zeichen erlaubt: "A-Z", "a-z", ".", Sonderzeichen: "ß, ä, ö, ü", "-", SPACE. ' +
        'Der Wert muss mit einem Großbuchstaben anfangen.',
        'ERROR_MAXLENGTH': 'Es sind maximal 50 Zeichen erlaubt',


        // ==== Centuria ===
        'CENTURIAS': 'Zenturien',
        'CENTURIA': 'Zenturie',

        // Properties
        'NBR_STUDENTS': 'Anzahl der Studenten',
        'PROGRAM': 'Studiengang',
        'SELECT_PROGRAM': 'Wählen Sie einen Studiengang',
        'YEAR': 'Jahrgang',
        'LETTER': 'Buchstabe',

        // Actions
        'CREATE_NEW_CENTURIA': 'Neue Zenturie anlegen',
        'ADD_CENTURIA': 'Zenturie anlegen',

        // Messages
        'CENTURIA_CREATION_SUCCESS': 'Die Zenturie wurde erfolgreich angelegt:',
        'CENTURIA_CREATION_FAILURE': 'Die Zenturie konnte nicht angelegt werden',

        // === Events ===
        'EVENT': 'Veranstaltung',
        'CAUSER': 'Verursacher',

        // Properties
        'DATE': "Datum",
        'BEGIN': 'Beginn',
        'END': 'Ende',
        'REPEAT_FOR_WEEKS': 'Wöchentlich wiederholen',

        // Actions
        'CREATE_NEW_EVENT_FOR_COURSE': 'Erstelle neue Veranstaltungen für den Kurs ',
        'EDIT_EVENT_OF_COURSE': 'Bearbeiten der Veranstaltung des Kurses',
        'CREATE_EVENT': 'Erstelle neue Veranstaltung',
        'FORCE_CREATE': 'Trotzdem erstellen',
        'FORCE_UPDATE': 'Trotzdem ändern',

        // Messages
        'COURSE_EVENTS_CREATION_SUCCESS': 'Die Veranstaltungen wurden erfolgreich erstellt.',
        'COURSE_EVENTS_CREATION_FAILURE': 'Es ist ein Fehler bei der Erstellung der Veranstaltungen aufgetreten:',
        'EVENT_UPDATE_SUCCESS': 'Das Event wurde erfolgreich geändert.',
        'EVENT_UPDATE_FAILURE': 'Es ist ein Fehler beim Ändern des Events aufgetreten.',

        // === Courses ===

        'COURSE': 'Kurs',
        'COURSES': 'Kurse',
        'SEMINAR': 'Seminar',
        'EXAM': 'Klausur',
        'ELECTIVE_MODULE': 'WPK',

        // Properties
        'COURSE_TYPE': 'Modulart',
        'COURSE_NAME': 'Name',
        'SELECT_COURSE_TYPE': 'Wählen Sie einen Kurs-Typ',
        'PARTICIPANTS_OPTIONAL': 'Teilnehmer (optional)',
        'PARTICIPANTS': 'Teilnehmer',
        'SEMINARS_NO_PARTICIPANTS': 'Seminare haben keine Teilnehmer.',

        // Actions
        'ADD_EVENTS': 'Veranstaltungen hinzufügen',
        'EDIT_COURSE': 'Kurs verändern',
        'CREATE_NEW_COURSE': 'Neuen Kurs anlegen',
        'ADD_COURSE': 'Kurs anlegen',
        'BACK_TO_COURSE': 'Zurück zum Kurs',

        // Messages
        'TUTOR_REQUIRED': 'Bitte erstellen Sie zuerst einen Dozenten, bevor Sie einen Kurs anlegen.',
        'COURSE_CREATION_SUCCESS': 'Der Kurs wurde erfolgreich angelegt. Sie können in der Kursansicht Veranstaltungen für den Kurs hinzufügen:',
        'COURSE_CREATION_FAILURE': 'Es ist ein Fehler beim Erstellen des Kurses aufgetreten: ',
        'COURSE_UPDATE_SUCCESS': 'Der Kurs wurde erfolgreich geändert.',
        'COURSE_UPDATE_FAILURE': 'Es ist ein Fehler beim Ändern des Kurses aufgetreten:',

        // === Room ===
        'ROOMS': 'Räume',
        'ROOM': 'Raum',
        'AVAILABLE_ROOMS': 'Verfügbare Räume',
        'FILTER_ROOMS': 'Räume filtern',

        // Room types
        'AUDIMAX': 'Audimax',
        'LECTURE': 'Hörsaal',
        'LAB': 'Labor',
        'COMPUTER': 'Computer-Raum',

        // Properties
        'BUILDING': 'Gebäude',
        'SELECT_BUILDING': 'Gebäude auswählen',
        'ROOM_NBR': 'Raumnummer',
        'ROOM_CAPACITY': 'Kapazität',
        'ROOM_TYPE': 'Raumtyp',
        'SELECT_ROOM_TYPE': 'Raumtyp auswählen',

        // Actions
        'CREATE_NEW_ROOM': 'Neuen Raum anlegen',
        'ADD_ROOM': 'Raum anlegen',

        // Messages
        'ROOM_CREATION_SUCCESS': 'Der Raum {{value}} wurde erfolgreich angelegt',
        'ROOM_CREATION_FAILURE': 'Der Raum konnte nicht angelegt werden',
        'NO_SUFF_LARGE_ROOM': 'Kein ausreichend großer Raum steht zur Verfügung',
        'ROOM_CREATED': 'Der Raum wurde erfolgreich erstellt:',

        // === Tutor ===
        'TUTORS': 'Dozenten',
        'TUTOR': 'Dozent',

        // Properties
        'TITLE': 'Titel',
        'FIRST_NAME': 'Vorname',
        'LAST_NAME': 'Nachname',

        // Actions
        'CREATE_NEW_TUTOR': 'Neuen Dozenten anlegen',
        'ADD_TUTOR': 'Dozent anlegen',
        'TUTOR_CREATION_SUCCESS': 'Der Dozent wurde erfolgreich angelegt:',
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
        'ENTITY_NOT_FOUND': 'Die Entität wurde nicht gefunden.',
        'INSUFFICIENT_SEATS': 'Es sind nicht ausreichend Sitzplätze in den Räumen der Veranstaltungen vorhanden, ' +
        'um alle Teilnehmer aufzunehmen. Bitte beachten Sie, dass zusätzliche Zeitkonflikte auftreten können, wenn ' +
        'Sie den Kurs dennoch ändern.',
        'OBJECT_HAS_EVENTS': 'Kann {{type}} ´nicht löschen, es gibt noch {{numberEvents}} referenzierte Kurse!',
        'TIME_CONFLICTS': 'Es gibt {{nbrOfConflicts}} in Konflikt stehende Veranstaltungen.',
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
