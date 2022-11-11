pipeline {
    agent any

    stages {
        
                stage('git') {
            steps {
            
                git branch: 'hajer', url: 'https://github.com/thamer-ctrl/DevOps.git',
                credentialsId:"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";
                
            }
}
        
       stage('MVN Package'){
            steps {
                sh """mvn -version  """
                sh """java -version """
               sh """mvn package -e """
            }
        }
        
      stage("MVN Compile"){
            steps {
                sh """mvn compile -e """
                
            }
        }
      stage("SONARQUBE"){
            steps {
                sh """mvn sonar:sonar """
                
            }
        }
        stage("Junit/Mockito"){
            steps {
                sh """mvn test """
                
            }
        }
        stage('Nexus'){
            steps{
                sh """mvn deploy """
            }
        }
           stage("MVN Install"){
            steps {
                sh """mvn install """
                
            }
        }
        stage("MVN Clean"){
            steps {
                sh """mvn clean -e """
                
            }
        }

    }
}
