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

/*stage('deploy to nexus') {
            steps {
                sh 'mvn -Dmaven.test.skip=true deploy'
            }
        }
*/

        stage('push docker hub') {
           steps {
               script {
                    echo "Docker push"
                     
                        sh 'docker login -u tassnime -p tassnime3107/'
                        sh 'docker push ${REGISTRY}:${TAG}'
                        sh 'docker logout'
                    
                }
            }
        }
  

stage('SonarQube analysis') {
            steps {

                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

     post {
       failure {
           emailext to: "tassnime.soussia0@gmail.com",
                    subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
                    body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}",
                   attachLog: true,
                    compressLog: true,
                    recipientProviders: [buildUser(), developers(), brokenBuildSuspects()]
       }
}
        
        

//        stage('maven clean install') {
//            steps {
//                echo 'maven clean install'
//                sh 'mvn clean install'
//            }
//        }
        
//      stage('deploy to nexus') {
//            steps {
//                sh 'mvn -Dmaven.test.skip=true deploy'
//            }
//        }

//        stage('push docker hub') {
//            steps {
//                script {
//                    echo "Docker push"
//                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
//                        sh 'docker login -u chjasser -p ${dockerhubpwd}'
////                        dockerImage.push()
//                        sh 'docker push ${REGISTRY}:${TAG}'
//                        sh 'docker logout'
//                    }
//                }
//            }
//        }
//
//        stage('clean install') {
//            steps {
//
//                    sh 'mvn clean install'
//
//            }
//        }


/*
        stage('Docker hub pull') {
            steps {
                script {
                    echo "Docker pull"
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u tassnime -p tassnime3107/'
                       dockerImage.pull()
                        sh 'docker pull ${REGISTRY}:${TAG}'
                        sh 'docker logout'
                    }

                }
            }
        }
*/

//        stage('run spring boot') {
//            steps {
//                echo "run spring boot"
//                sh 'mvn spring-boot:run'
//            }
//        }
//        stage('build project') {
//            steps {
//                echo "build project"
//                sh 'mvn  package'
//            }
//        }

//        stage('stop containers') {
//            steps {
//                sh 'docker-compose down'
//                sh 'docker-compose ps -a'
//            }
//
//        }

//        stage('Dokcer Cleaning up') {
//            steps {
//                sh "docker rmi ${REGISTRY}:${TAG}"
//            }
//        }
//
//                    stage('install') {
//                        steps {
//                            echo 'install'
//                            sh 'mvn  -Dmaven.test.skip=true  clean install '
//
//                        }
//
//                    }

}
}