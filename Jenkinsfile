pipeline {
    agent any
	
 environment {
        registryCredential = 'dockerhub'
        
        registry = 'tassnime/tpachat'
    }

    stages {
        stage('Getting the project from GIT') {
            steps {
               echo 'Test';
            }
        }
        
        stage('MVN CLEAN') {
            steps {
               
              script {

                  sh 'mvn clean'

 
                      }
                   }        
         }
       
         stage('MVN INSTALL') {
            steps {
               
              script {

                  sh 'mvn install'

 
                      }
                   }        
         }
stage('Test unitaire') {
            steps {
                script {
                    
                sh 'mvn test'  
                }

		        
            } 
           
        }
	
         
	    
          stage('MVN package') {
            steps {
                script {
                    
                sh 'mvn package'  
                }

		        
            } 
           
        }

stage('nexus') {
            steps {
               
              script {

sh 'mvn deploy -e'                      }
                   }         
         }

 stage('Building our image') {
  steps {
               
sh 'docker build -t tassnime/tpachat .'
               
            }
        }
	    
   
          		stage('Docker login') {
                                steps {
                                    script {

                                        sh 'docker login -u tassnime -p tassnime3107/'}
                                }
                                }
                          stage('Pushing Docker Image') {
                                steps {
                                    script {

                                     sh 'docker push tassnime/tpachat'
                                    }
                                }
                          }
                          stage('Run Spring && MySQL Containers') {
                                steps {
                                    script {
                                      sh ' docker-compose up -d '
                                    }
                                }
                            }
     }
     post {
         always {
            junit(testResults: 'target/surefire-reports/*.xml', allowEmptyResults : true)
        }
         success {
            emailext attachLog: true, body: '''End of Pipeline
            Finished: SUCCESS''', subject: '#buildsuccess', to: 'tassnime.soussia@esprit.tn'
         }
         failure  {
            emailext attachLog: true, body: '''End of Pipeline
            Finished: FAILURE''', subject: '#buildFailure', to: 'tassnime.soussia@esprit.tn'
         }
     
    }




}
}