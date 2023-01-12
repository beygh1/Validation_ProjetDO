pipeline {
    agent any

       stages {
        stage('Clone source code from Git') {
            steps {
                echo "Cloning Project from GitHub; Branch : achrefBranch"
                git branch: 'achrefBranch',
                url: 'https://github.com/beygh1/Validation_ProjetDO.git'
            }
        }
         stage('Unit Testing') {
            steps {
              
                sh 'mvn test' 
            }
        }
        stage('Integration Testing') {
            steps {
             
                sh  'mvn verify  -DskipTests'
            }
        }
        stage('MVN CLEAN') {
            steps {
                sh 'mvn -version'
                sh 'mvn clean'
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                //sh 'mvn sonar:sonar -Dsonar.projectKey=achref-key -Dsonar.host.url=http://192.168.56.2:9000 -Dsonar.login=293485fbadeb05e888a8f29eac6e24cf992b0b21'
                sh"mvn package -B -DskipTests sonar:sonar -Dsonar.host.url=http://192.168.56.2:9000 -Dsonar.login=293485fbadeb05e888a8f29eac6e24cf992b0b21"
            }
        }
        stage('MVN DEPLOY') {
            steps {
                sh 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit -DartifactId=achat -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://192.168.56.2:8081/repository/maven-releases/ -Dfile=target/achat-1.0.jar'
            }
        }
        // stage('Docker Image Build') {
        //     steps{
        //         script{
        //             sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
        //             sh 'docker image tag $JOB_NAME:v1.$BUILD_ID medhedimansouri/$JOB_NAME:v1.$BUILD_ID'
        //             sh 'docker image tag $JOB_NAME:v1.$BUILD_ID medhedimansouri/$JOB_NAME:latest'
        //         }
        //     }
        // }

    }
    post {

        always {

     

            emailext (

                    to: "achref.djebbi@esprit.tn",

                    replyTo: "achref.djebbi@esprit.tn",

                    subject: "BuildResult ${currentBuild.currentResult}",

                    mimeType: 'text/html',

                    body: "test"

            )

        }

    }
}