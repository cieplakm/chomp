pipeline {
agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
                sh 'mvn dockerfile:build'

            }
        }

        stage('building_images') {
                    steps {
                        sh 'docker save -o image.tar chomp'
                    }
                }
    }
}