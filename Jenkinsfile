pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                sh "rm -rf  Validation_ProjetDO"
                sh "git clone https://github.com/beygh1/Validation_ProjetDO.git"
                sh "cd Validation_ProjetDO"
                sh "mvn clean -f Validation_ProjetDO"
            }
        }
        stage('install') {
            steps {
               sh "mvn install -f Validation_ProjetDO"
            }
        }
        stage('test') {
            steps {
                sh "mvn test -f Validation_ProjetDO"
            }
        }
        stage('package') {
            steps {
                sh "mvn package -f Validation_ProjetDO"
            }
        }
    }
}
