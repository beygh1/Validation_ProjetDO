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
        
         stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=key -Dsonar.host.url=http://192.168.1.19:9000 -Dsonar.login=69e41706ac5fb3c1a19c2116b6aec8c0e3128d5e'
            }
        }
        
         stage('test') {
            steps {
                sh "mvn test -B"
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
