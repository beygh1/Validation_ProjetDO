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
    }
}