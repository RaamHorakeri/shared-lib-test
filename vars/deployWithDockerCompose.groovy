def call() {
    script {
        echo "Deploying application with Docker Compose"
        sh "docker compose up -d"
    }
}
