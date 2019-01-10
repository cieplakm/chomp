pipeline {
agent any
    stages {
        stage('build') {
            steps {
            sh 'apt-get update && apt-get install -y maven'
                sh 'mvn clean install'
                sh 'mvn dockerfile:build'

            }
        }

        stage('building_images') {
                    steps {
                        sh 'docker save -o image.tar chomp'
                        sh 'docker cp 81bd8bf639f8:image.tar image.tar'
                        sh 'docker load -i image.tar'
                    }
                }
    }
}