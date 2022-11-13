pipeline {
    agent any
    environment {
        TAG = '3.0'
	REGISTRY = 'tassnime/tpachat'
    }

    stages {

      stage('maven clean') {
            steps {
                echo 'maven clean'
                sh 'mvn  clean'
            }
        }

     stage('maven build') {
            steps {
                echo "build project"
                sh 'mvn -Dmaven.test.skip=true   package'
            }
       }
       
        stage('maven test') {
            steps {
                echo 'unit test'
                sh 'mvn test'
            }
        }
stage('Checking Maven version'){
            steps{
                echo "Checking Mavin version";
                sh "mvn -version"
            }
        }




stage('build docker image') {
           steps {
                script {
                    echo "Docker build image"
                    dockerImage = docker.build("${REGISTRY}:${TAG}")
                    
                }
            }
        }

stages {
	    
                stage ('Git') {
            steps {
                
               
               git branch: 'tassnime', 
                url: 'https://github.com/thamer-ctrl/DevOps.git'
                 
		        
            }
        }

stage('run containers') {
            steps {
                script {
                    echo "Docker build image"
                    sh 'docker-compose up -d'
                    sh 'docker-compose ps'
                }
            }
        }

stage('NEXUS'){
            steps{
                echo "nexus"
                sh ' mvn deploy -DskipTests=true'
            }
}


}

