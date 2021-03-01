
pipeline {

   agent any


   stages {

      stage('Build') {

         steps {

            // Get some code from a GitHub repository

           // git 'https://github.ibm.com/Rajni-Rajput/library1.git'


            // Run Maven on a Unix agent.

           // sh "./mvnw -Dmaven.test.failure.ignore=true clean package"


            // To run Maven on a Windows agent, use

             bat "mvn -Dmaven.test.failure.ignore=true clean package"

         }
         stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy Image') {
      steps{
         script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            }    
          }
        }
      }
  /*  stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    } */
    stage('Run Helm') {
      steps {
      script {      
      container('helm') {
        sh "helm ls"
       }
      } 
      }

  
        
         post {

            // If Maven was able to run the tests, even if some of the test

            // failed, record the test results and archive the jar file.

            success {

               junit '**/target/surefire-reports/TEST-*.xml'

               archiveArtifacts 'target/*.jar'

            }

         }

      }

   }

}
