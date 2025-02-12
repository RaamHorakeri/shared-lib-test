def call(String appName) {
    script {
        echo "Building Docker image: ${appName.toLowerCase()}"
        sh "docker build --no-cache -t ${appName.toLowerCase()}:latest ."
    }
}
