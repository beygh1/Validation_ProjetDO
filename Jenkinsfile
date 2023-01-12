pipeline {
    agent any

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'achrefBranch', url: 'https://github.com/beygh1/Validation_ProjetDO.git'
            }
        }
        stage('Unit Testing') {
            steps {
                sh 'mvn test' 
            }
        }
        stage('Integration Testing') {
            steps {
                sh  'mvn verify -DskipUnitTests -DskipTests'
            }
        }
        stage('Maven Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=sonar-api-key -Dsonar.host.url=http://${localhost}:9000 -Dsonar.login=b400fade54c7c756f2d837fd97d7619290f43057'
            }    
        }
        stage('MVN DEPLOY') {
            steps {
                sh 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit -DartifactId=achat -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://${localhost}:8081/repository/maven-releases/ -Dfile=target/achat-1.0.jar'
            }
        }
        stage('Doker Image Build') {
            steps{
                script{
                    sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                    sh 'docker image tag $JOB_NAME:v1.$BUILD_ID medhedimansouri/$JOB_NAME:v1.$BUILD_ID'
                    sh 'docker image tag $JOB_NAME:v1.$BUILD_ID medhedimansouri/$JOB_NAME:latest'
                }
            }
        }
    }
}