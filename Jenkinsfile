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
                sh 'mvn sonar:sonar -Dsonar.projectKey=achref-key -Dsonar.host.url=http://192.168.1.19:9000 -Dsonar.login=293485fbadeb05e888a8f29eac6e24cf992b0b21'
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
