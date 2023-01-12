pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                
                sh "git clone https://github.com/beygh1/Validation_ProjetDO.git"
                sh "cd Validation_ProjetDO"
                sh "mvn clean"
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
    }
}
