pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    environment {
        APP_NAME = 'jenkins-demo'
        APP_VERSION = "1.0.${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                echo "Code checked out successfully"
            }
        }

        stage('Build') {
            steps {
                echo "Building Maven project..."
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo "Running unit tests..."
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "Packaging application..."
                sh 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar',
                                     fingerprint: true
                }
            }
        }

        stage('Verify') {
            steps {
                sh '''
                    echo "Build artifacts:"
                    ls -lh target/*.jar
                    echo "JAR file size:"
                    du -sh target/*.jar
                '''
            }
        }
    }

    post {
        always {
            echo "Maven build completed — Status: ${currentBuild.currentResult}"
        }
        success {
            echo "JAR artifact created: target/${APP_NAME}-1.0-SNAPSHOT.jar"
        }
        failure {
            echo "Build failed — check Maven output above for errors"
        }
    }
}
