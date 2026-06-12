pipeline {
    agent any

    environment {
        APP_NAME = 'jenkins-demo'
        BUILD_VERSION = "1.0.${BUILD_NUMBER}"
    }

    stages {

        stage('Pull Code') {
            steps {
                echo "=========================================="
                echo "STAGE 1: Pulling code from GitHub"
                echo "=========================================="
                echo "Repository : ${GIT_URL}"
                echo "Branch     : ${GIT_BRANCH}"
                echo "Commit     : ${GIT_COMMIT}"
                echo "Workspace  : ${WORKSPACE}"
                sh 'ls -la'
            }
        }

        stage('Build') {
            steps {
                echo "=========================================="
                echo "STAGE 2: Building the application"
                echo "=========================================="
                echo "App Name   : ${APP_NAME}"
                echo "Version    : ${BUILD_VERSION}"
                sh '''
                    echo "Simulating build process..."
                    echo "Compiling source files..."
                    sleep 2
                    echo "Build complete!"
                    echo "${BUILD_VERSION}" > build-output.txt
                    echo "Build artifact created: build-output.txt"
                '''
            }
        }

        stage('Test') {
            steps {
                echo "=========================================="
                echo "STAGE 3: Running Tests"
                echo "=========================================="
                sh '''
                    echo "Running unit tests..."
                    echo "Test 1: Login module        - PASSED"
                    echo "Test 2: Payment module      - PASSED"
                    echo "Test 3: User profile module - PASSED"
                    echo "Test 4: API endpoints       - PASSED"
                    echo "All 4 tests passed!"
                    echo "Test coverage: 85%"
                '''
            }
        }

        stage('Code Quality') {
            steps {
                echo "=========================================="
                echo "STAGE 4: Code Quality Check"
                echo "=========================================="
                sh '''
                    echo "Running code quality analysis..."
                    echo "Checking code style..."
                    echo "Checking for vulnerabilities..."
                    echo "Code quality score: 92/100"
                    echo "No critical issues found!"
                '''
            }
        }

        stage('Archive') {
            steps {
                echo "=========================================="
                echo "STAGE 5: Archiving Build Artifacts"
                echo "=========================================="
                sh '''
                    echo "Archiving artifacts..."
                    ls -la
                    echo "Build artifact: build-output.txt"
                    cat build-output.txt
                '''
                archiveArtifacts artifacts: 'build-output.txt', fingerprint: true
            }
        }
    }

    post {
        success {
            echo "=========================================="
            echo "PIPELINE COMPLETED SUCCESSFULLY!"
            echo "App: ${APP_NAME} v${BUILD_VERSION}"
            echo "=========================================="
        }
        failure {
            echo "=========================================="
            echo "PIPELINE FAILED!"
            echo "Check the logs above for errors."
            echo "=========================================="
        }
        always {
            echo "Pipeline finished at: ${new Date()}"
        }
    }
}
