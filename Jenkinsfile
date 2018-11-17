node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/PSEH-9/r2d2-starwars.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'M3'
   }
   stage('Compile & Test') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean compile test"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean compile test/)
      }
   }
   stage('Package') {
         // Run the maven build
         if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package "
         } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
         }
      }
   stage('Deploy') {
      sh "chmod 644 jenkins.pem"
      sh "scp -r -i jenkins.pem target/*.jar  ubuntu@ec2-18-216-165-122.us-east-2.compute.amazonaws.com:/home/ubuntu/workspace/r2d2"
   }

}