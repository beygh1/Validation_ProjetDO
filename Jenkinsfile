pipeline {
  agent any
  environment {
    EMAIL_RECIPIENTS = "mohamedelhedi.mansouri@esprit.tn"
    mvnHome = tool 'M2_HOME'
	JavaHome = tool 'JAVA_HOME'
	registry= "medhedimansouri/integration_project"
	registryCredential = 'dockerHubId'
	dockerImage = 'achat'
    localhost="192.168.0.8"
  }

    stages {
        stage('Clone Repo') {
            steps {
                script{
                        checkout([$class: 'GitSCM', branches: [[name: 'jenkinsFileHedi']],
                                  doGenerateSubmoduleConfigurations: false,gitTool: 'Default', extensions: [[$class: 'CleanBeforeCheckout'],
                                                                                                              [$class: 'CloneOption', timeout: 1000, depth: 1, reference: '', shallow: true]] ,
                                  submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitlabLogin',
								   url: 'https://github.com/beygh1/Validation_ProjetDO.git']]])
                   
                }
            }

        }
		stage("Run Build & Tests parallel") {
			failFast true
			parallel {
                stage('Build Project Skipping tests') {
                    steps {
                        script{
                                timestamps {
                                        sh "${mvnHome}/bin/mvn clean deploy -B -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://${localhost}:8081/repository/maven-releases/"
                                }
                        }
                    }
                }
                
                stage('Run Tests') {
                    steps {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            script{   
                                    timestamps { 
                                            sh "${mvnHome}/bin/mvn test -B"
                                    }
                            
                            }
                        }
                    }
                }
		
			}
		 }
		stage('SonarQube') {
            steps {
                script{
                        timestamps {
                                sh "${mvnHome}/bin/mvn package -B -DskipTests sonar:sonar -Dsonar.host.url=http://${localhost}:9000 -Dsonar.login=b400fade54c7c756f2d837fd97d7619290f43057"
                        }
                  
                }
            }
        }

		stage('Building Doker Image') {
            steps {
                script{
                        timestamps {
                          dockerImage = docker.build registry + ":$BUILD_NUMBER"
                        }
                }
            }   
        }
	
	    stage('Deploy Image') {
            steps {
                script{
                        timestamps {
                          docker.withRegistry ('', registryCredential ) {
							  dockerImage.push()
                          }
                        }
                }
            }
        }
	
        stage('Cleaning up Image') {
            steps {
                    script{
                            timestamps {
                                sh "docker rmi $registry:$BUILD_NUMBER" 
                            }
                    
                    }
            }    
        }
	
		stage('Running Docker Compose') {
            steps {
                script{
                        timestamps {
                          sh "docker-compose -f docker-compose.yml up -d" 
                        }
                }
            } 
        }
    }
    post {
        always {
            emailext (
                    to: "${EMAIL_RECIPIENTS}",
                    replyTo: "${EMAIL_RECIPIENTS}",
                    subject: "[BuildResult][${currentBuild.currentResult}] - Job '${env.JOB_NAME}' (${env.BUILD_NUMBER})",
                    mimeType: 'text/html',
                    body: '''${JELLY_SCRIPT, template="custom-html.jelly"}'''
            )
        }
    }
	
    options {
        buildDiscarder(logRotator(numToKeepStr: '4'))
        timeout(time: 60, unit: 'MINUTES')
    }
}
