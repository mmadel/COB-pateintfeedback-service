pipeline{
  agent any
  stages{
    stage("build"){
      steps{
        echo 'building application'
        echo 'Application built'
        echo 'Application built webhook'
      }
    }
      stage("test"){
      steps{
        echo 'testing application'
      }
    }
    
      stage("deploy"){
      steps{
        echo 'deploying application'
      }
    }
  }
}
