def call(String appName) {
    script {
        echo "Building Docker image: ${appName.toLowerCase()}"
        sh "cd reddit-clone-k8s-ingress-main"
        sh "docker build --no-cache -t ${appName.toLowerCase()}:latest ."
    }
}
