@Library('shared-lib') _

pipeline {
    agent any

    parameters {
        choice(name: 'APP_NAME', choices: ['account', 'bifrost', 'community', 'content', 'payment'], description: 'Select Microservice')
        string(name: 'BRANCH', defaultValue: 'main', description: 'Git branch to checkout')
        string(name: 'REPO_URL', description: 'Git repository URL')
        string(name: 'CREDENTIALS_ID', description: 'Jenkins credentials ID')
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    checkoutFromGit(params.BRANCH, params.REPO_URL, params.CREDENTIALS_ID)
                }
            }
        }

        stage('Load Secrets') {
            steps {
                script {
                    loadSecrets(params.APP_NAME)
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    buildDockerImage(params.APP_NAME)
                }
            }
        }

        stage('Remove Existing Container') {
            steps {
                script {
                    removeContainer(params.APP_NAME)
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    deployWithDockerCompose()
                }
            }
        }

        stage('Cleanup') {
            steps {
                script {
                    pruneDockerImages()
                }
            }
        }
    }

    post {
        always {
            echo 'Deployment complete.'
        }
        success {
            echo 'Deployment succeeded.'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
