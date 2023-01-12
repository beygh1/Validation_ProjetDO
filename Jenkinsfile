pipeline {
    agent any
      environment {
        localhost = "192.168.56.2"
        dockerImage = "achat"
        registry= "achref2023/repodocker"
        registryCredential = "dockerhub"
    }


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
             
                sh  'mvn verify'
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
                sh"mvn package -B -DskipTests sonar:sonar -Dsonar.host.url=http://${localhost}:9000 -Dsonar.login=293485fbadeb05e888a8f29eac6e24cf992b0b21"
            }
        }
        stage('MVN DEPLOY') {
            steps {
                sh 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit -DartifactId=achat -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://${localhost}:8081/repository/maven-releases/ -Dfile=target/achat-1.0.jar'
            }
        }
        stage('BUILD') { 
            steps { 
                script { 
       
                    dockerImage = docker.build registry
                    
                }
            } 
        }
        stage('PUSH DOCKERHUB') { 
            steps { 
             
                        timestamps {
						  docker.withRegistry ('', registryCredential ) {
							  dockerImage.push()
                        }
                    } 
                
            } 
            
        // }
        //  stage('RMV IMG') {
        //     steps {
        //         sh "docker rmi $registry:latest"
        //     }
        // }
        //    stage('DOCKER-COMPOSE') {
        //     steps {
        //         sh 'docker-compose down --remove-orphans'
        //         sh 'docker-compose -f docker-compose.yml up -d'
        //         sh 'docker restart spring-boot-docker-container'
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