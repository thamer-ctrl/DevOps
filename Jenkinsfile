pipeline {
    agent any

    stages {
        
                stage('git') {
            steps {
            
                git branch: 'hajer', url: 'https://github.com/thamer-ctrl/DevOps.git',
                credentialsId:"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";
                
            }
}
      stage("MVN Clean"){
            steps {
                sh """mvn clean -e """
                
            }
        }  
       stage('MVN Package'){
            steps {
                sh """mvn -version  """
                sh """java -version """
               sh """mvn package -e """
            }
        }
        
       stage("Junit/Mockito"){
            steps {
                sh """mvn test """
                
            }
        }
      stage("SONARQUBE"){
            steps {
                sh """mvn sonar:sonar """
                
            }
        }
       
        stage('Nexus'){
            steps{
                sh """mvn deploy """
            }
        }
           stage('Docker build')
        {
            steps {
                 sh 'docker build --build-arg IP=0.0.0.0 -t hajersaidi/achatback  .'
            }
        }
        stage('Docker login')
        {
            steps {
                sh 'echo $dockerhub_PSW | docker login -u hajersaidi -p dckr_pat_1XTH7dQVwz_yuf0KrwuymdQvRJU'
            }    
       
        }
      stage('Push') {

			steps {
				sh 'docker push hajersaidi/achatback'
			}
		}
        
       stage('Run app With DockerCompose') {
              steps {
                  sh "docker-compose -f docker-compose.yml up -d  "
              }
              }
       stage('Email notification'){
           steps {
            mail bcc: '', body: '''Hello Hajer, It's Jenkins,
            Your Devops Pipeline is succeeded.
            Best Regards''', cc: '', from: '', replyTo: '', subject: 'Devops Pipeline', to: 'hajer.saidi@esprit.tn'
            }
       }
        

    }
}
