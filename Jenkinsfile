pipeline {
agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install -DskipTests'
                sh 'mvn dockerfile:build'
                sh 'docker run -d -p 8080:8080 chomp'
            }
        }
    }
}