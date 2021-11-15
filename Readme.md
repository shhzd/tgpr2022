4049 - techniques de gestion de projets
Enseignant : Benoît Penelle
Année universitaire 2020-2021.
EPFC

1. Information générales

    Application console, de gestion d'inscription et de test d'une école.

    Nom de l'application : Moudeule
    Framework : MVC EPFC
    Serveur dB : 10.4.21-MariaDB - mariadb.org binary distribution
    Language : Java SDK 11.0.8 (syntax level 11)
        Librairies : awt-color-factory-1.0.1.jar
                     jline-2.14.6.jar
                     mariadb-java-client-2.7.3.jar
                     slf4j-api-1.8.0-beta4.jar
                     slf4j-simple-1.8.0-beta4.jar
                     text-io-ben.jar

2. Membres du groupes G03

    Agim 	 Berisha
    Younnes  Chebli
    Gultekin Gonen
    Linh P.  Nguyen
    Shahzad  Rahman
    Silvère  Sayag

3. UC de l'application

    Certaine user story ont été develloppées, consulter le tableau Trello : https://trello.com/b/pBZETczi/tgpr-2122-g03.
	
    Developpé pour release 1
	
        UC_StartMenu							Silvère
        UC_Signup								Younnes		
        UC_Login								Agim		
        UC_StudentMainMenu						Agim		
        UC_StudentAvailableCoursesList			Linh	
        UC_StudentCourseDescription				Linh		
        UC_TeacherEditCourse					Younnes		
        UC_StudentTestsList						Gultekin
        UC_StudentActiveTest					Gultekin		
        UC_TeacherMainMenu						Silvère		
        UC_StudentEditCourse					Younnes		
        UC_TeacherManageStudentRegistration		Silvère	
        UC_TeacherAddStudent					Agim		
        UC_TeacherAddCourse						Silvère		
        UC_TeacherQuizzesList					Shahzad	
        UC_TeacherAddQuiz						Shahzad		
		
    Restant à developper

        UC_TeacherEditQuizQuestion				
        UC_TeacherEditQuiz				
        UC_TeacherQuizTestsList				
        UC_StudentClosedTest				
        UC_TeacherQuizTestDetail				

    Developement annulé ou à considerer

        UC_Debug								Si on a le temps		
        UC_TeacherDeleteStudent					Deprecated		
        UC_TeacherCoursesList					Deprecated		


4. Utilisateurs de l'application

    pseudo	password	fullname			birthdate	role

    admin	admin		Mr. Admin			NULL		admin
    ben		ben			Benoît Penelle		1970-07-01	teacher
    bibi	bibi		Bibi the Robot		NULL		student
    bob		bob			Bob l'éponge		NULL		student
    boris	boris		Boris Verhaegen		NULL		teacher
    bruno	bruno		Bruno Lacroix		NULL		teacher
    caro	caro		Caroline			NULL		student
    fred	fred		Frédéric			NULL		student
    guest	guest		Invité				NULL		student
    harry	harry		Harry Cover			NULL		student
    marc	marc		Marc Michel			NULL		teacher
    p		p			Prof Dev.			NULL		teacher
    s		s			Etudiant Dev.		NULL		student


5. Choix de l'équipe concernant le fonctionnement de l'application

    - Le wireframe de l'application se trouve dans /doc/Moudeule_User_Interface.pdf
    - Les menus utilisent des chiffres de préférence à des initiale, à l'exception des options de navigation
    - Les instruction d'utilisation du GIT se trouvent dans un document séparé : /doc/Git_Instructions.txt
	
6. Bugs connus