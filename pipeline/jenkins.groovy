pipeline {
    agent any
    parameters {
        choice(name: 'OS', choices: ['linux', 'darwin', 'windows'], description: 'Pick OS')
        choice(name: 'ARCH', choices: ['amd64', 'arm64'], description: 'Pick Architecture')
    }
    environment {
        REPO = 'https://github.com/suhovius/kbot'
        BRANCH = 'main'
        SCHEME = 'https://'
        REGISTRY = 'docker.io'
        REPOSITORY = 'suhovius/kbot'
        
        TARGETOS = "${params.OS}"
        TARGETARCH = "${params.ARCH}" 
    }
    stages {
        stage("clone") {
            steps {
                echo 'CLONE REPOSITORY'
                git branch: "${BRANCH}", url: "${REPO}"
            }
        }

        stage("test") {
            steps {
                echo 'TEST EXECUTION STARTED'
                sh 'make test'
            }
        }

        stage("build") {
            steps {
                echo 'BUILD EXECUTION STARTED'
                sh 'make build'
            }
        }

        stage("image") {
            steps {
                script {
                    echo 'BUILD IMAGE EXECUTION STARTED'
                    sh 'make image'
                } 
            }
        }

        stage("push") {
            steps {
                script {
                    docker.withRegistry('', 'dockerhub') {
                        sh 'make push'
                    }
                }
            }
        }
    }
}