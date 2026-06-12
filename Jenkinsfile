pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        timestamps()
        timeout(time: 10, unit: 'MINUTES')
        disableConcurrentBuilds()
    }

    environment {
        APP_NAME    = 'jenkins-demo'
        APP_VERSION = "2.0.${BUILD_NUMBER}"
        DEPLOY_ENV = 'production'
    }

    triggers {
        githubPush()
    }

    stages {

        stage('Checkout') {
            steps {
                echo "=============================="
                echo "Checking out source code..."
                echo "=============================="
                checkout scm
                sh 'git log -1 --pretty=format:"%h - %s (%an)"'
            }
        }

        stage('Environment Info') {
            steps {
                echo "=============================="
                echo "Environment Information"
                echo "=============================="
                sh '''
                    echo "App Name    : $APP_NAME"
                    echo "App Version : $APP_VERSION"
                    echo "Deploy Env  : $DEPLOY_ENV"
                    echo "Build No    : $BUILD_NUMBER"
                    echo "Node Name   : $NODE_NAME"
                    echo "Workspace   : $WORKSPACE"
                    echo "Java Version:"
                    java -version
                '''
            }
        }

        stage('Parallel Tests') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        echo "Running Unit Tests..."
                        sh '''
                            echo "Unit Test 1: Auth Service    - PASSED"
                            echo "Unit Test 2: User Service    - PASSED"
                            echo "Unit Test 3: Order Service   - PASSED"
                            echo "Unit Tests completed!"
                        '''
                    }
                }
                stage('Integration Tests') {
                    steps {
                        echo "Running Integration Tests..."
                        sh '''
                            echo "Integration Test 1: API Gateway  - PASSED"
                            echo "Integration Test 2: DB Connection - PASSED"
                            echo "Integration Tests completed!"
                        '''
                    }
                }
                stage('Security Scan') {
                    steps {
                        echo "Running Security Scan..."
                        sh '''
                            echo "Scanning for vulnerabilities..."
                            echo "CVE Check: No critical issues"
                            echo "OWASP Check: Passed"
                            echo "Security Scan completed!"
                        '''
                    }
                }
            }
        }

        stage('Build') {
            steps {
                echo "=============================="
                echo "Building Application"
                echo "=============================="
                sh '''
                    echo "Building ${APP_NAME} v${APP_VERSION}..."
                    sleep 1
                    echo "Build SUCCESS"
                    echo "${APP_NAME}-${APP_VERSION}" > artifact.txt
                    cat artifact.txt
                '''
            }
        }

        stage('Deploy to Staging') {
            when {
                expression { env.DEPLOY_ENV == 'staging' }
            }
            steps {
                echo "=============================="
                echo "Deploying to STAGING"
                echo "=============================="
                sh '''
                    echo "Deploying ${APP_NAME} v${APP_VERSION} to staging..."
                    echo "Deployment complete!"
                '''
            }
        }

        stage('Deploy to Production') {
            when {
                expression { env.DEPLOY_ENV == 'production' }
            }
            steps {
                echo "This stage only runs when DEPLOY_ENV=production"
                sh 'echo "Production deployment..."'
            }
        }

        stage('Manual Approval') {
            when {
                expression { env.DEPLOY_ENV == 'staging' }
            }
            steps {
                input message: 'Staging looks good? Approve to finish pipeline.',
                      ok: 'Yes, Approve!'
            }
        }

    }

    post {
        always {
            echo "=============================="
            echo "Pipeline completed!"
            echo "Build    : ${BUILD_NUMBER}"
            echo "Status   : ${currentBuild.currentResult}"
            echo "Duration : ${currentBuild.durationString}"
            echo "=============================="
        }
        success {
            echo "SUCCESS: ${APP_NAME} v${APP_VERSION} pipeline passed!"
        }
        failure {
            echo "FAILURE: Pipeline failed at ${currentBuild.currentResult}"
        }
        unstable {
            echo "UNSTABLE: Pipeline completed with warnings"
        }
    }
}
