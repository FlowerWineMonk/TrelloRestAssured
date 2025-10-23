pipeline {
    agent any

    tools {
        jdk 'jdk21'
        gradle 'gradle8'
    }

    environment {
        ALLURE_RESULTS_DIR = "build/allure-results"
        ALLURE_REPORT_DIR = "build/reports/allure-report"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checkout to source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Running Gradle build..."
                sh './gradlew clean build -x test'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests and generating Allure results..."
                sh './gradlew test'
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                    allure([
                        includeProperties: false,
                        jdk: '',
                        results: [[path: "$ALLURE_RESULTS_DIR"]]
                    ])
                }
            }
        }

        stage('Allure Report') {
            steps {
                echo "Building Allure Report"
                sh './gradlew allureReport'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'build/reports/allure-report/**', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed."
        }
        failure {
            echo "Pipeline failed check logs and test reports."
        }
    }
}