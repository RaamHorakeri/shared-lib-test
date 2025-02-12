def call(String appName) {
    script {
        echo "Removing existing container: ${appName.toLowerCase()}"
        sh "docker rm -f ${appName.toLowerCase()} || true"
    }
}
