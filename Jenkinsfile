pipeline {
    agent any

    tools {
        // Asigură-te că Maven este configurat în "Global Tool Configuration" cu numele 'M3'
        maven 'M3'
        jdk 'Java17'
    }

    stages {
        stage('Checkout') {
            steps {
                // Pasul de clonare a codului
                git branch: 'main', url: 'https://github.com/user-ul-tau/hapifyMe-tests.git'
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