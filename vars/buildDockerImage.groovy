// def call(String appName) {
//     script {
//         echo "Building Docker image: ${appName.toLowerCase()}"
        
//         sh "docker build --no-cache -t ${appName.toLowerCase()}:latest ."
//     }
// }


def call(String appName) {
    script {
        echo "Building Docker image: ${appName.toLowerCase()}"
        
        // Use the correct path to the Dockerfile
        sh "docker build --no-cache -t ${appName.toLowerCase()}:latest -f reddit-clone-k8s-ingress-main/Dockerfile ."
    }
}
