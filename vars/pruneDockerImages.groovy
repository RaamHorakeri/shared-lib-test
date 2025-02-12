def call() {
    script {
        echo "Pruning unused Docker images"
        sh "docker image prune -f"
    }
}
