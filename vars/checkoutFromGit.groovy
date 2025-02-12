// def call(String branch, String repoUrl, String credentialsId) {
//     checkout([
//         $class: 'GitSCM',
//         branches: [[name: "*/${branch}"]],
//         userRemoteConfigs: [[url: repoUrl, credentialsId: credentialsId]]
//     ])
// }


// vars/cloneAndNavigate.groovy
def call(String branch, String repoUrl, String credentialsId, String subDir) {
    // Checkout the repository with the specified branch and credentials
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        userRemoteConfigs: [[url: repoUrl, credentialsId: credentialsId]]
    ])
    
    // Navigate to the specified sub-directory and run commands
    dir(subDir) {
        // Example command to run in the sub-directory
        sh "echo 'Inside ${subDir} directory'"
    }
}
