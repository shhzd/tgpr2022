4049 - techniques de gestion de projets
Enseignant : Benoît Penelle
Année universitaire 2020-2021.
EPFC

**Note : veuillez lire le Readme sur Bitbucket pour la mise en forme de certains caractères tels que le [:heavy_check_mark:]**

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

3. Règles métiers imposées

    - Tous les utilisateurs du système se connectent au moyen d’un identifiant unique et d’un mot de passe.
    - Les étudiants peuvent s'inscrire dans l'application.

    - Les étudiants peuvent voir le titre et le descriptif de tous les cours.
    - Les étudiants peuvent s'incrire à un cours, cette inscrption doit être activé par un professeur.
    - Un étudiant n’a accès qu’aux quiz des cours auxquels pour lesquels il a été activé.
    - Chaque cours a une capacité maximum d’étudiants, définie par le professeur.
    - Un étudiant peut se désinscrire d'un cours.
    - Le professeur peut inscrire lui-même des étudiants à son cours.
    - Le professeur peut activer et désactiver les inscriptions à ses cours.
    - Le professeur peut supprimer une inscription, libérant ainsi une place, et effacer tous les test éventuels.

    - Le professeur est responsable des quiz de son cours.
    - Un quiz est composé de QRM et que QCM.
    - Les seules réponses possibles ont Vrai ou Faux.
    - Un quiz doit avoir une période de validité.
    - Un étudiant ne peut participer à un quiz (remplir un test) que durant la période de validité.
    - La date à laquelle un étudiant répond à un test doit être enregistrée sans pouvoir être modifiée.
    - Un étudiant peut répondre à un test, ou éditer ses réponses pendant toute la période de validité d'un quiz.
    - Un étudiant peut voir les réponse d'un test auquel il a participé après la date de clôture
    - Les questions sont proposées dans un ordre aléatoire.
    - Les propositions sont proposées dans un ordre aléatoire au sein de leurs questions.
    - Un professeur peut voir tout les tests passés et en cours des cours dont il a la charge.

4. Règles métiers décidé par l'équipe

    - Seul le professeur titulaire peut confirmer un candidat à son cours.
    - Les ID de cours sont compris entre 1000 et 9999.
    - Le code d'un cours est toujours en majuscule. 
    - Plusieurs cours peuvent avoir le même code.
    - Un professeur peut choisir une capacité inférieure au nombre d'inscrit, mais il est prévenu du surnombre.
    - Un quiz doit comporte au moins une question comprenant au moins deux propositions.
    - Un professeur ne peut changer la date de début d'un quiz si celle-ci est dans le passé.
    - Un professeur est autorisé à changer une question ou une proposition jusqu'à la fin d'un quiz.
    - Les cours ont une capacité fixée entre 4 et 32

5. UC de l'application

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


6. Utilisateurs de l'application

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


7. Documents explcatifs et choix relatifs à l'application

    - Le wireframe de l'application se trouve dans /doc/Moudeule_User_Interface.pdf
    - Les menus utilisent des chiffres de préférence à des initiale, à l'exception des options de navigation
    - Les instruction d'utilisation du GIT se trouvent dans un document séparé : /doc/Git_Instructions.txt
	
   1. Bugs connus

      [:heavy_check_mark:] GENERAL : Nom de l'application
      [:x:] GENERAL : Passage d'un controleur à l'autre - bug global sur certaines UC au niveau du retour, les Controllers ne se ferment pas comme il le faudrait (superposition des controllers ?) certains UC ont été corrigés, mais la solution n'a pas encore été implementée sur toute l'application
        [:heavy_check_mark:] UC Login : impossible de quitter la boucle
      [:x:] UC_TeacherEditCourse : modifier l'ID d'un cours provoque une duplication dans la base de données
        [:heavy_check_mark:] UC_TeacherEditCourse : ajouter try catch dans chaque édition (afin de permettre d'interrompre une édition en cours)
      [:heavy_check_mark:] UC_TeachereditCourse : ajouter la capacité valide dans le message d'erreur
      [:heavy_check_mark:] UC_TeacherManageStudent : fautes d'orthographe
      [:heavy_check_mark:] UC_TeacherManageStudent : suppression en cascade des tests lors de la suppression d'un étudiant **(Merci d'avoir ajouté **ON DELETE CASCADE** dans le script SQL)**
      [:x:] UC_TeacherAddQuizz : validation métier et filtrage des champs vides
      [:x:] UC_TeacherQuizList : problème de rafraichissement de la liste des quiz après la création d'un nouveau quizz (liste non mise à jour)
      [:x:] UC_TeacherQuizList : appel d'un cours n'existant pas dans la liste
      [:x:] UC_AddCourse : ne pas utiliser error mais pause
      [:x:] UC_ViewCourse : Suppression des tests et quiz des cours supprimés
        [:heavy_check_mark:] UC_TeacherEditQuiz : suppression en cascade (Re-merci d'avoir ajouté **ON DELETE CASCADE** dans le script SQL)
      [:x:] UC_TeacherAddStudent : sélection impossible n>9
   UC_TeacherEditQuiz : Lorsqu'un quiz a déjà débuté, on ne peut changer la date de début.


