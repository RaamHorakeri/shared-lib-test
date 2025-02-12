def call() {
    script {
        echo "Deploying application with Docker Compose"
        // sh "docker compose up -d"
      sh "docker-compose -f ./reddit-clone-k8s-ingress-main/docker-compose.yaml up -d"


    }
}
