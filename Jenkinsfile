pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
               bat "rmdir  /s /q Validation_ProjetDO"
                bat "git clone https://github.com/beygh1/Validation_ProjetDO.git"
                bat "mvn clean -f Validation_ProjetDO"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f Validation_ProjetDO"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f Validation_ProjetDO"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f Validation_ProjetDO"
            }
        }
    }
}
