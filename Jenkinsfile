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
          ]]])
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
          ]) {
            sh 'mvn clean install -DskipTests -s ./settings.xml'
          }

        }
      }
      stage('构建镜像并推送到 CODING Docker 制品库') {
          steps {
            sh "docker build -t ${CODING_DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION} -f ${DOCKERFILE_PATH} ${DOCKER_BUILD_CONTEXT}"
            useCustomStepPlugin(
              key: 'codingcorp:artifact_docker_push',
              version: 'latest',
              params: [
                image:"${CODING_DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION}",
                repo:"${DOCKER_REPO_NAME}"
              ]
            )
          }
        }
    }
    environment {
       CODING_DOCKER_REG_HOST = "${CCI_CURRENT_TEAM}-docker.pkg.${CCI_CURRENT_DOMAIN}"
       CODING_DOCKER_IMAGE_NAME = "${PROJECT_NAME.toLowerCase()}/${DOCKER_REPO_NAME}/${DOCKER_IMAGE_NAME}"
    }
  }