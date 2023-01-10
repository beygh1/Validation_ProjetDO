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
        stage('Build Project Skipping tests') {
            steps {
                script{
                        timestamps {
                                sh 'mvn clean deploy -B -DskipTests -DaltDeploymentRepository=nexus::default::http://192.168.1.188:8081/repository/maven-snapshots/'
                        }
                  
                }
            }

        }


    }

}