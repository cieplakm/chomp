pipeline {
agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
                sh 'mvn dockerfile:build'
                sh 'docker save -o image.tar chomp'
            }
        }
    }
}