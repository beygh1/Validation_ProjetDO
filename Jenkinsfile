pipeline {
    agent any
    environment {
        EMAIL_RECIPIENTS = "mohamedelhedi.mansouri@esprit.tn"
        mvnHome = tool 'MAVEN_384'
        JavaHome = tool 'JAVA8_HOME'
        registry= "bessem8/timesheet"
        registryCredential = 'dockerHub'
        dockerImage = ''
        localhost="192.168.1.188"
    }
    stages {

        stage('Git Checkout') {

            steps {
                git branch: 'jenkinsFileHedi', url: 'https://github.com/beygh1/Validation_ProjetDO.git'
            }
        }
        stage('Unit Testing') {
            steps {
                //sh 'mvn test'
                sh 'mvn test -DskipTests' 
            }
        }
        stage('Integration Testing') {
            steps {
                //sh  'mvn verify -DskipUnitTests'
                sh  'mvn verify -DskipUnitTests -DskipTests'
            }
        }
        stage('Maven Build') {
            steps {
                //sh  'mvn clean install'
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script{
                    withSonarQubeEnv(credentialsId: 'sonar-api-key') {
                     sh 'mvn clean package sonar:sonar -DskipTests'
                 }
                }    
            }
        }
        stage('MVN DEPLOY') {
            steps {
                sh 'mvn clean package -DskipTests deploy:deploy-file -DgroupId=tn.esprit -DartifactId=achat -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://${localhost}:8081/repository/maven-releases/ -Dfile=target/achat-1.0.jar'
            }
        }



    }

}