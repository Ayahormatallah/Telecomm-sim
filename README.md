TelecommSim est une application développée pour simuler et comparer les performances des protocoles de communication TCP, UDP et SCTP. Ce projet est conçu pour fournir un outil d'apprentissage et d'analyse pour les chercheurs, étudiants et professionnels du secteur des télécommunications.

## Table des matières

- [Introduction](#introduction)
- [Architecture du logiciel](#architecture-du-logiciel)
- [Docker Image](#docker-image)
- [Backend](#backend)
- [Frontend](#frontend)
- [Démarrage](#demarrage)
- [Contribuer](#Contributeurs)


## Introduction

Les protocoles de communication sont essentiels pour assurer la transmission efficace des données dans un réseau. Ce projet a pour but de permettre aux utilisateurs de simuler et analyser les performances des protocoles TCP, UDP, et SCTP en ajustant différents paramètres comme la taille des paquets, la vitesse de connexion, et la perte de paquets.

## Architecture du logiciel

L'architecture de TelecommSim repose sur plusieurs modules qui collaborent pour offrir une expérience interactive et fluide. Voici un diagramme représentant l'architecture du logiciel :

![Image Alt](https://github.com/Ayahormatallah/Telecomm-sim/blob/01a59356e2352cb879c823cf68529dfc08233f36/diagram-export-27-12-2024-22_08_40.png).

Le **back-end** est construit en utilisant **Spring Boot**, avec **Thymeleaf** pour le front-end. Le système utilise **MySQL** pour la gestion des données et **Spring Security** pour sécuriser les requêtes.

## Docker Image

yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: telecomm_sim
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: 123
      MYSQL_DATABASE: telecomm_sim
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      timeout: 15s
      retries: 10
    ports:
      - "3307:3306"
    volumes:
      - mysql-data:/var/lib/mysql
    networks:
      - telecomm-network

  backend:
    build:
      context: ./telecomm-sim-back
      dockerfile: Dockerfile
    container_name: telecomm-backend
    restart: always
    depends_on:
      mysql:
        condition: service_healthy
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://telecomm_sim:3306/telecomm_sim
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 123
      SERVER_PORT: 8085
    ports:
      - "8085:8085"
    networks:
      - telecomm-network

volumes:
  mysql-data:

networks:
  telecomm-network:


## Backend
**Spring Boot** : Framework Java basé sur Spring qui simplifie le développement des applications Java. Utilisé pour gérer la logique métier, les services REST, et les interactions avec la base de données.
- **MySQL** : Système de gestion de base de données relationnelle (SGBD) utilisé pour stocker les informations et les résultats de simulation. La base de données est connectée à l'application via Spring Data JPA pour simplifier les opérations CRUD.
- **Spring Security** : Framework de sécurité utilisé pour sécuriser les API et protéger les données sensibles contre les accès non autorisés.
  ## Frontend
  **HTML** : Utilisé pour structurer le contenu de l'application.
- **CSS** : Employé pour le style et la mise en page de l'interface utilisateur.
- **Thymeleaf** : Moteur de templates Java utilisé côté serveur pour rendre des vues dynamiques dans l'application. Il permet de générer du HTML côté serveur tout en préservant la logique de l'application côté backend.
- **Js**: pour les graphes
# Backend Structure

The backend of the **TelecommSim** application is organized as follows:

### 1. **Controller**
   - LoginController: Handles requests related to user authentication and management (e.g., login, logout).
   - PageController: Manages the main application pages or views, handling access to different sections of the app.
   - SimulationController: Manages requests and the logic related to protocol simulations. It processes the simulation based on user-provided parameters.
   - UserController: Responsible for managing user information, such as account creation, updating user details, etc.

 2. **Entity**
   - User: Represents the user entity in the database, including attributes such as user ID, roles, and other relevant user information.
   - SimulationResult: Represents the results of a simulation in the database, containing performance metrics such as throughput, latency, etc.

 3. **Repository**
   - SimulationResultRepository: Interface for managing simulation results in the database. It provides methods for performing CRUD (Create, Read, Update, Delete) operations on simulation results.
   - UserRepository: Interface for managing users in the database, including methods to search, insert, and update user information.

5. **Service**
   - CustomUserDetailsService: A service used for managing security and user details, specifically for retrieving user data during login and authorization processes.
   - SimulationService: Contains the business logic for handling protocol simulations. This service includes methods to calculate simulation results based on user-defined parameters.
   - ProtocolSimulation: Contains the logic responsible for simulating specific communication protocols (e.g., TCP, UDP, SCTP).
   - SecurityConfig: A configuration file for handling security-related settings, including user authorization and authentication.
   - SimulationRequest: Represents a simulation request, including the parameters sent by the user to execute a simulation.
   - SimulationResult: Stores the results of a simulation, which can also be linked with the entity for data storage.
   - TelecommSimApplication: The main entry point of the Spring Boot application, containing the main method that runs the application.

## Dependencies
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>telecomm-sim</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>telecomm-sim</name>
	<description>Simulation de Protocoles de Communication</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<!-- Spring Boot dependencies -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.33</version>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<!-- BCrypt for password encoding -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-crypto</artifactId>
		</dependency>

		<!-- Testing dependencies -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.mockito</groupId>
					<artifactId>mockito-core</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-junit-jupiter</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.vintage</groupId>
			<artifactId>junit-vintage-engine</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- DevTools for live reload -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<mainClass>com.example.telecomm.TelecommSimApplication</mainClass>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
### Demarrage
# Démarrage

Pour configurer et exécuter **TelecommSim** localement, suivez ces étapes :

## Prérequis

Assurez-vous que les éléments suivants sont installés sur votre machine :

1. **Docker** : Ce projet utilise Docker pour gérer le backend et le conteneur de la base de données.
   - Installez Docker depuis [ici](https://www.docker.com/get-started).

2. **XAMPP** (pour le serveur MySQL) :
   - Installez XAMPP depuis [ici](https://www.apachefriends.org/download.html).
   - Assurez-vous que MySQL est installé et fonctionne dans XAMPP (bien que Docker soit utilisé pour la base de données, vous pouvez vouloir confirmer sa configuration si vous exécutez localement).


## Configuration du backend 

1. **Démarrer les serveurs XAMPP Apache et MySQL** :

   Ouvrez XAMPP et démarrez les services Apache et MySQL. Assurez-vous que MySQL fonctionne, car il sera utilisé par le backend pour stocker les résultats de simulation.

2. **Construire et démarrer avec Docker** :

   Assurez-vous que Docker fonctionne, puis naviguez vers le dossier contenant le fichier **docker-compose.yml** dans votre terminal.

   Exécutez les commandes suivantes pour construire les conteneurs Docker et démarrer l'environnement :

   docker-compose up
   et sur navigateur http://localhost:8085

### Video demonstrative


https://github.com/user-attachments/assets/c920c9a4-93ba-407d-b333-940ab051867f



## Contributeurs :

- **Aya Hormatallah** ()
- **Achouak Bougrine**(https://github.com/AchouakBougrine/TelecommSim.git)
