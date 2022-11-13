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
}
}