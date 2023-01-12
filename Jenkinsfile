pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                sh "rm -rf  Validation_ProjetDO"
                sh "git clone https://github.com/beygh1/Validation_ProjetDO.git"
                
                sh "mvn clean"
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
      
        stage('package') {
            steps {
                sh "mvn package"
            }
        }
    }
}
