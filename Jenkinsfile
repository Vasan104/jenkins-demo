pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    environment {
        APP_NAME    = 'java-app'
        APP_VERSION = "1.0.${BUILD_NUMBER}"
        JAR_NAME    = 'java-app.jar'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        timestamps()
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {

        stage('Checkout') {
            steps {
                echo "=============================="
                echo "Checking out source code"
                echo "=============================="
                checkout scm
                sh '''
                    echo "Repository files:"
                    ls -la
                    echo "Java source files:"
                    find src -name "*.java" 2>/dev/null || echo "No java files found yet"
                '''
            }
        }

        stage('Code Analysis') {
            steps {
                echo "=============================="
                echo "Analysing source code"
                echo "=============================="
                sh '''
                    echo "Java files found:"
                    find src/main -name "*.java" | wc -l
                    echo "Test files found:"
                    find src/test -name "*.java" | wc -l
                    echo "Lines of code:"
                    find src/main -name "*.java" -exec wc -l {} +
                '''
            }
        }

        stage('Compile') {
            steps {
                echo "=============================="
                echo "Compiling Java source code"
                echo "=============================="
                sh 'mvn clean compile -q'
                sh '''
                    echo "Compiled classes:"
                    find target/classes -name "*.class" 2>/dev/null
                '''
            }
        }

       stage('Unit Test') {
    steps {
        echo "Running Unit Tests..."
        sh 'mvn test -Dsurefire.timeout=300'
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
        stage('Package') {
            steps {
                echo "=============================="
                echo "Packaging Java Application"
                echo "=============================="
                sh 'mvn package -DskipTests -q'
                sh '''
                    echo "Generated JAR:"
                    ls -lh target/*.jar
                    echo "JAR contents (first 10 entries):"
                    jar tf target/${JAR_NAME} | head -10
                '''
            }
        }

        stage('Run Application') {
            steps {
                echo "=============================="
                echo "Running the Java Application"
                echo "=============================="
                sh '''
                    echo "Executing java-app.jar..."
                    java -jar target/${JAR_NAME}
                '''
            }
        }

        stage('Archive') {
            steps {
                echo "=============================="
                echo "Archiving Build Artifacts"
                echo "=============================="
                sh '''
                    echo "Final artifact:"
                    ls -lh target/${JAR_NAME}
                    echo "Build version: ${APP_VERSION}"
                    echo "${APP_NAME}-${APP_VERSION}" > build-info.txt
                    cat build-info.txt
                '''
                archiveArtifacts artifacts: 'target/*.jar, build-info.txt',
                                 fingerprint: true
            }
        }
    }

    post {
        always {
            echo "=============================="
            echo "Pipeline finished!"
            echo "App     : ${APP_NAME}"
            echo "Version : ${APP_VERSION}"
            echo "Status  : ${currentBuild.currentResult}"
            echo "Duration: ${currentBuild.durationString}"
            echo "=============================="
        }
        success {
            echo "Java application built and run successfully!"
        }
        failure {
            echo "Pipeline failed — review the stage that turned red"
        }
    }
}
