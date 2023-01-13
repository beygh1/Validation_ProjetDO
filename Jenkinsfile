pipeline {
    agent any
    environment {
        imagename = "salimbhk/achat"
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
                sh 'mvn sonar:sonar -Dsonar.projectKey=Project_maven -Dsonar.host.url=http://192.168.1.184:9000 -Dsonar.login=69e41706ac5fb3c1a19c2116b6aec8c0e3128d5e'
            }
        }
        
        stage('package') {
            steps {
                sh "mvn package"
            }
        }
        
         stage('test') {
            steps {
                sh "mvn test"
            }
        }      
        
        stage('install') {
            steps {
               sh "mvn install"
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
                docker.withRegistry('',registryCredential){
                    dockerImage.push()
                }
            }
          }
       }    
        stage('Docker compose up') {
            steps {
              sh "docker-compose up -d"
            }
        }
    }
}   
