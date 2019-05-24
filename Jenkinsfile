@Library('jenkins-shared-libraries') _
 
pipeline {
    agent { label 'slave_linux_sie' }
 
    tools {
        maven "maven-3.6.0"
        jdk 'openjdk-11'
    }
 
    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '2'))
    }
 
    triggers {
        pollSCM 'H/30 * * * *'
    }
 
    stages {
        stage('Clean') {
            steps {
                deleteDir()
            }
        }
 
        stage('Clone') {
            steps {
                checkout scm
            }
        }
 
        stage('Build') {
            steps {
                mvnDeploy()
            }
        }

        stage('Sonar') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
 
    post {
      always {
        sendNotifications currentBuild.result
      }
    }
 
}
