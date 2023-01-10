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
                sh 'mvn test' 
            }
        }
        // stage('Integration Testing') {

        //     steps {
        //         sh  'mvn verify -DskipUnitTests'
        //     }
        // }
        // stage('Maven Build') {

        //     steps {
        //         sh  'mvn clean install'
        //     }
        // }
    //     stage('Static Code Analysis') {

    //         steps {
    //             script{
    //                 withSonarQubeEnv(credentialsId: 'sonar-api-key') {
    //                  sh 'mvn clean package sonar:sonar'
    //              }
    //             }    
            
    //     }
    // }

}