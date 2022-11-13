pipeline {
    agent any

    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('Pulling & Checking Git Branch ') {
            steps {
                echo "Pulling & Checking Git Branch";
                git branch: 'dhia',
                url:'https://github.com/thamer-ctrl/DevOps.git';
            }
        }
        stage('Checking Maven version'){
            steps{
                echo "Checking Mavin version";
                sh "mvn -version >> Mavenversion.csv"
            }
        }
        stage('Cleaning Maven install'){
            steps {
                echo "Cleaning Maven install";
                sh 'mvn -X clean install'
            }
        }
        
         stage('Junit'){
            steps{
                echo "Junit";
                sh "mvn test"
            }
        }
        
        stage('Compiling Project'){
            steps {
                echo "Compiling Project";
                sh 'mvn compile  -DskipTests >> Compiling.csv'
            }
        }
        
        stage ('SonarQube analysis'){
            steps {
                echo "SonarQube analysis";
                sh "mvn sonar:sonar -Dsonar.projectKey=sonar -Dsonar.host.url=http://192.168.1.17:9000 -Dsonar.login=ec6606943155dd62ef5aadd11f47dc8f8b21f39b"
            }
        }
        
        stage('Deploying to Nexus') {
            steps {
                echo "Deploying to Nexus";
                sh 'mvn  -X deploy >> Deploying.csv'
            }
        }
        
        stage("Login to Docker") {
            steps{
                echo "Login to Docker";
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u dhiaabdelli2 -p "dhia.abdelli@esprit.tn"'
            }
        }
        
        stage("Building Local Docker Image ") {
            steps{
                echo "Building Local Docker Image";
                sh 'docker build -t dhiaabdelli2/projetdevops .'
            }
        }
        
        stage("Pushing Project to DockerHub") {
            steps{
                echo "Pushing Project to DockerHub";
                sh 'docker push dhiaabdelli2/projetdevops'
            }
        }
        
        stage("Running Docker-compose in Background") {
            steps{
                echo "Running Docker-compose in Background";
                sh 'docker-compose up -d'
            }
        } 
    }
post {
            always{
                archiveArtifacts artifacts: '*.csv', onlyIfSuccessful: true
                
                emailext to: "dhia.abdelli@esprit.tn",
                subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
                body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}",
                attachmentsPattern: '*.csv'
                
            cleanWs()
            }
        }
}