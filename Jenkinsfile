pipeline {
    agent any
    //environment {
        //TAG = '3.0'
	//REGISTRY = 'tassnime/tpachat'
    //}

    stages {

      stage('maven clean') {
            steps {
                echo 'maven clean'
                sh 'mvn  clean'
            }
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

}
