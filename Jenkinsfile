pipeline {
    agent any

    tools {
        // Asigură-te că Maven este configurat în "Global Tool Configuration" cu numele 'M3'
        maven 'Maven 3.8.x'
        jdk 'JDK 21' // Rezolvă eroarea 'invalid target release: 21'
    }

    stages {
        stage('Checkout') {
            steps {
                // Pasul de clonare a codului
                git branch: 'main', url: 'https://github.com/gdatcu/qaSkillab-S22.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Începem rularea testelor pentru HapifyMe...'
                // Comanda de rulare a testelor.
                // '-Dtest=LoginTest' este opțional, rulează doar un test specific
                sh 'mvn clean test'
            }
        }
        stage('Archive Results') {
            steps {
                // Salvăm rapoartele JUnit standard și orice fișier din surefire-reports
                junit '**/target/surefire-reports/*.xml'

                // Arhivăm tot folderul target pentru a-l putea descărca manual
                archiveArtifacts artifacts: 'target/*.jar, target/surefire-reports/*', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline-ul a terminat execuția.'
        }
        success {
            echo 'Yey! Testele HapifyMe au trecut cu succes!'
        }
        failure {
            echo 'Alertă: Ceva nu a funcționat la endpoint-ul login_register.php'
        }
    }
}