pipeline {
  agent any
  tools {
     jdk 'JAVA_HOME'
     maven 'M2_HOME'    
  }
  
  stages {
  

    stage('Cleaning ') {
      steps {
         sh '''echo "*********Clean the Project is processing ....*********
		mvn clean'''
      }
    }
    stage ('compilation') {
            steps {
                sh '''echo "*********compilation ....*********"
			mvn -DskipTests compile'''
            }
        }
    stage("SonarQube ") {
            steps {
              withSonarQubeEnv('SonarQube') {
                sh 'mvn clean -DskipTests package sonar:sonar'
		echo "*********SonarQube analysis finished with SUCCESS *********"
              }
            }
    }
    
    stage ('Construction de livrable') {
            steps {
                sh 'mvn -DskipTests package'
            }
        }
    stage("NEXUS") {
			steps {
				sh 'mvn clean deploy -DskipTests'
				echo "*********NEXUS deployement finished with SUCCESS *********"
          }
        }

    stage('Docker build ') {
      steps {
        sh 'docker build -t ${dockerhubusername}/app .'
      }
    }

     stage('Docker login') {
      steps {
         sh 'echo "*********Docker login is processing ....*********"'
        sh 'docker login --username ${dockerhubusername} --password ${dockerhubpassword}'

      }
    }
    stage('Docker push') {
      steps {
         sh 'echo "*********Docker push is processing ....*********"'
        sh 'docker push ${dockerhubusername}/app'

      }
    }        
    stage('Docker Compose') {
      		steps {
         		sh 'docker-compose up -d'
			echo "*********Apllication Is Started *********"
      }
    }
    
    stage('Junit/Mockito Testing') {
      steps {
         sh 'echo "*********Junit / Mockito Test is processing .... *********"'
        sh 'mvn  test'
      }
    }


  }
}
