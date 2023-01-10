pipeline {
    agent any
    stages {

        stage('Git Checkout') {

            steps {
                git branch: 'jenkinsFileHedi', url: 'https://github.com/beygh1/Validation_ProjetDO.git'
            }
        }
        stage('Unit Testing') {

            steps {
                sh 'mvn test -DskipTests' 
            }
        }
        // stage('Integration Testing') {

        //     steps {
        //         sh  'mvn verify -DskipUnitTests'
        //     }
        // }
        stage('Maven Build') {

            steps {
                //sh  'mvn clean install'
                sh 'mvn clean package -DskipTests'
            }
        }
        // stage('SonarQube Analysis') {

        //     steps {
        //         script{
        //             withSonarQubeEnv(credentialsId: 'sonar-api-key') {
        //              sh 'mvn clean package sonar:sonar'
        //          }
        //         }    
        //     }
        // }

    }

}