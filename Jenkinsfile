pipeline {
    agent any
    tools {
        maven "Maven 3.0.5" 
    }
    stages {

        stage('Git Checkout') {

            steps {
                git branch: 'jenkinsFileHedi', url: 'https://github.com/beygh1/Validation_ProjetDO.git'
            }
        }
        stage('Unit Testing') {

            // steps {
            //     sh 'mvn test' 
            // }
            steps {
                sh 'mvn clean package -DskipTests=true' 
            }
        }
    }
}