pipeline {
    agent any

    tools {
        maven 'Maven'          // Name of Maven installation in Jenkins
        jdk 'JDK17'            // Name of JDK installation in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/YOUR-USERNAME/CucumberSeleniumBDDFramework_Flipkart.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dbrowser=chrome'
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/ExtentReport',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])

                // Optional: Cucumber Report
                cucumber buildStatus: 'UNSTABLE',
                         fileIncludePattern: '**/cucumber.json',
                         jsonReportDirectory: 'target'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*', allowEmptyArchive: true
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}