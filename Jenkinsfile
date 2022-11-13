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
	    
   
       
   stage('Push Dockerhub') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        sh "docker push $registry"
                    }
                    
                }
                
            }
            
        }
	    stage('Run Spring et MySQL Containers') {
                                steps {
                                    script {
                                      sh ' docker-compose up -d '
                                    }
                                }
                            }

}
}