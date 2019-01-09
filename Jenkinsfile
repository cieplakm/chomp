pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
            steps {
                                    sh 'mvn dockerfile:build'
                                }

                                steps {
                                                        sh 'docker save -o image.tar'
                                            }
        }

    }
}