def call(String appName) {
    script {
        def serviceSecrets = [
            'account': ['SENDGRID_KEY', 'MONGO_CONNECTION_STRING'],
            'bifrost': ['BIFROST_ACCOUNT_PROFILE_API', 'ODIN_SECRET', 'ODIN_HOST'],
            'community': ['REDIS_URL', 'JWT_SECRET', 'PAYMENT_API_KEY'],
            'content': ['ANALYTICS_API_KEY', 'STRIPE_SECRET_KEY', 'GOOGLE_OAUTH_CLIENT'],
            'payment': ['AWS_ACCESS_KEY', 'AWS_SECRET_KEY', 'S3_BUCKET_NAME']
        ]

        if (!serviceSecrets.containsKey(appName)) {
            error "No secrets defined for service: ${appName}"
        }

        def credentialsList = serviceSecrets[appName].collect { credId ->
            string(credentialsId: credId, variable: credId)
        }

        withCredentials(credentialsList) {
            serviceSecrets[appName].each { credId ->
                env[credId] = eval(credId)
            }
            echo "Secrets loaded for ${appName}"
        }
    }
}
