pipeline {
    agent any
    environment {
        imagename = "Beya/achat"
        registryCredential = 'dockerhub'
        dockerImage = ''
}
    stages {
        stage('git repo & clean') {
            steps {
                sh "rm -rf  Validation_ProjetDO"
                sh "git clone https://github.com/beygh1/Validation_ProjetDO.git"
                
                sh "mvn clean"
            }
        }
        stage('compile') {
            steps {
                sh "mvn compile"
            }   
            
        }
        
         stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=sp -Dsonar.host.url=http://192.168.1.145:9000 -Dsonar.login=5bc31d7e3491082079f4fa1ade767e8f794e8c83'
            }
        }
        
        stage('install') {
            steps {
               sh "mvn install"
            }
        }    
        
         stage('test') {
            steps {
                sh "mvn test"
            }
        }
        
        stage('package') {
            steps {
                sh "mvn package"
            }
        }
        
        stage('NEXUS DEPLOY') {
            steps {
              sh "mvn deploy"
            }
             }
               stage('Building image') {
          steps{
            script {
              dockerImage = docker.build imagename + ":$BUILD_NUMBER"
              }
            }
       }
       stage('Deploy image') {
          steps{
            script {
                docker.withRegistry('',registryCredentials){
                    dockerImage.push()
                }
            }
          }
       }    

    }
}   
