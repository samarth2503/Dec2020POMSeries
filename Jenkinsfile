pipeline { 
agent any 
    stages { 
        
        stage ('Build') { 
            steps{
                echo "Building the test automation for demo cart app"
            }
        }
        
        stage('Test') {
            steps {
		
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                	bat "git credentialsId: 'gitCred', url: 'https://github.com/samarth2503/Dec2020POMSeries'"
                    bat "mvn clean install"
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'build', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }
        
    }
    post{
         
         always{
            echo "Sending Email....." 
            emailext body: '$DEFAULT_CONTENT', subject: '$DEFAULT_SUBJECT', to: 'samarthjain680@gmail.com'
         }
     }

 }
