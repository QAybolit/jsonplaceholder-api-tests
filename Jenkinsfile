pipeline {
    agent any

    parameters {
        string(name: "BRANCH_NAME", defaultValue: 'main', description: "Пропишите ветку, в которой будут запускаться тесты")
        choice(name: "TASK", choices: ['test', 'regress'], description: "Выберите таску для запуска тестов")
    }

    stages {

        stage("Очистка окружения") {
            cleanWs()
        }

        stage("Копирование кода из репозитория") {
            git branch: "${BRANCH_NAME}",
                url: 'https://github.com/QAybolit/jsonplaceholder-api-tests.git'
        }

        stage("Запуск тестов") {
            sh "./gradlew clean ${TASK}"
        }

        stage("Генерация Allure-отчета") {
            sh './gradlew allureReport'
        }
    }

    post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'build/allure-results']]
                ])
        }
    }
}