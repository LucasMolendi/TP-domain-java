# TP de création et d'utilisation d'un Domain
## Objectif 
Lors du TP l'objectif était de mettre en pratique les enseignement sur les bonnes pratique et allez plus loin que de gérer un API responsive.
nous avons donc utiliser *Gradle* 
et créer 3 fichier reconnu par *Gradle* dans ``settings.gradle`` :
- ``core-domain``
- ``adapter-jpa``
- ``adapter-mongo``

le sujet complet : [ici](https://chocolaterie.github.io/documentation/docs/spring/tp/domain/tp-1/)

## Information retourné dans le controller

### ``getAll``
2002 | Tout les articles ont été récupérer avec succès

### ``getId``
#### Si l'article est trouvé :
2002 | l'article a été récupérer avec succès
#### Si l'article est introuvable :
7001 | article inconnu

### ``delete``
#### Si l'article est bien supprimé :
2002 | article supprimé avec succès
#### Si l'article n'existe pas :
7001 | article inconnu

### ``save``
#### Si l'id n'existe pas :
**Création réussie**  → 2002 | Article crée avec siccès, Félicitations !!

**Titre existant** → 7006 | Titre déjà utilisé
#### si l'id existe déjà : 
**Mise a jour réussie** → 2003 | Article modifié avec succès

**Titre existant** → 7006 | Le titre est déja utilisé
