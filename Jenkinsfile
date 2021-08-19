pipeline {
  agent any
  stages {
    stage('检出') {
      steps {
        checkout([
          $class: 'GitSCM',
          branches: [[name: GIT_BUILD_REF]],
          userRemoteConfigs: [[
            url: GIT_REPO_URL,
            credentialsId: CREDENTIALS_ID
          ]]
        ])
      }
    }
    stage('编译') {
      steps {
        echo '开始构建'
        withCredentials([
          usernamePassword(
            credentialsId: "${CODING_ARTIFACTS_CREDENTIALS_ID}",
            usernameVariable: 'CODING_ARTIFACTS_USERNAME',
            passwordVariable: 'CODING_ARTIFACTS_PASSWORD'
          )
        ])
        {
          sh 'mvn clean install -DskipTests -s ./settings.xml'
        }
      }
    }
    stage('构建并推送镜像') {
      steps {
        script {
          docker.withRegistry("https://${DOCKER_REGISTRY_HOSTNAME}", "${DOCKER_REGISTRY_CREDENTIAL}") {
            // 确保仓库中有可用的 Dockerfile
            dir("${DOCKER_BUILD_DIR}"){
              sh "docker build -t ${DOCKER_REPOSITORY_NAME}:${DOCKER_IMAGE_NAME} ."
            }
            docker.image("${DOCKER_REPOSITORY_NAME}:${DOCKER_IMAGE_NAME}").push()
          }
        }
      }
    }

//     stage("部署到远端服务") {
//       steps {
//         script {
//           def remoteConfig = [:]
//           remoteConfig.name = "my-remote-server"
//           remoteConfig.host = "${REMOTE_HOST}"
//           remoteConfig.port = "${REMOTE_SSH_PORT}".toInteger()
//           remoteConfig.allowAnyHosts = true
//
//           withCredentials([
//             sshUserPrivateKey(
//               credentialsId: "${REMOTE_CRED}",
//               keyFileVariable: "privateKeyFilePath"
//              ),
//             usernamePassword(
//               credentialsId: "${CODING_ARTIFACTS_CREDENTIALS_ID}",
//               usernameVariable: 'CODING_DOCKER_REG_USERNAME',
//               passwordVariable: 'CODING_DOCKER_REG_PASSWORD'
//             )
//           ])
//           {
//            // SSH 登陆用户名
//             remoteConfig.user = "${REMOTE_USER_NAME}"
//            // SSH 私钥文件地址
//             remoteConfig.identityFile = privateKeyFilePath
//
//            // 请确保远端环境中有 Docker 环境
//             sshCommand(
//               remote: remoteConfig,
//               command: "docker login -u ${CODING_DOCKER_REG_USERNAME} -p ${CODING_DOCKER_REG_PASSWORD} ${CODING_DOCKER_REG_HOST}",
//               sudo: true,
//             )
//
//             sshCommand(
//               remote: remoteConfig,
//               command: "docker rm -f java-spring-app | true",
//               sudo: true,
//             )
//
//            // DOCKER_IMAGE_VERSION 中涉及到 GIT_LOCAL_BRANCH / GIT_TAG / GIT_COMMIT 的环境变量的使用
//            // 需要在本地完成拼接后，再传入到远端服务器中使用
//             DOCKER_IMAGE_URL = sh(
//               script: "echo ${CODING_DOCKER_REG_HOST}/${CODING_DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION}",
//               returnStdout: true
//             )
//
//             sshCommand(
//               remote: remoteConfig,
//               command: "docker run -d -p 8080:8080 --name java-spring-app ${DOCKER_IMAGE_URL}",
//               sudo: true,
//             )
//
//             echo "部署成功，请到 http://${REMOTE_HOST}:8080 预览效果"
//           }
//         }
//       }
//     }

  }

  environment {
    DOCKER_REGISTRY_HOSTNAME = "${TCR_REGISTRY_HOSTNAME}"
    DOCKER_REGISTRY_CREDENTIAL = "${TCR_REGISTRY_CREDENTIAL}"
    DOCKER_REPOSITORY_NAME = "${TCR_NAMESPACE_NAME}/${TCR_REPOSITORY_NAME}"
    DOCKER_IMAGE_NAME = "${TCR_IMAGE_NAME}"
  }
}