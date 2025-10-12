pipeline {
    agent any

    parameters {
        string(name: "BRANCH_NAME", defaultValue: 'main', description: "Пропишите ветку, в которой будут запускаться тесты")
        choice(name: "TASK", choices: ['test', 'regress'], description: "Выберите таску для запуска тестов")
    }

    stages {

        stage("Очистка окружения") {
            steps {
                cleanWs()
            }
        }

        stage("Копирование кода из репозитория") {
            steps  {
                git url: 'https://github.com/QAybolit/jsonplaceholder-api-tests.git', branch: "${BRANCH_NAME}"
            }
        }

        stage("Запуск тестов") {
            steps {
                sh("./gradlew clean ${TASK}")
            }
        }

        stage("Генерация Allure-отчета") {
            steps {
                sh("./gradlew allureReport")
            }
        }
    }

    post {
        always {
            allure includeProperties: false,
                   jdk: '',
                   results: [[path: 'build/allure-results']]
        }
    }
}