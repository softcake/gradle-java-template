#!/bin/bash
# This script will build the project.

#git fsck --full

EXIT_STATUS=0

function strongEcho {
  echo ""
  echo "================ $1 ================="
}

if [ "$TRAVIS_SECURE_ENV_VARS" == "true" ]; then

	strongEcho 'Decrypt secret key file'
	
	openssl aes-256-cbc -pass pass:$SIGNING_PASSPHRASE -in secring.gpg.enc -out secring.gpg -d
	gpg --keyserver keyserver.ubuntu.com --recv-key $SIGNING_KEY

fi

if [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_SECURE_ENV_VARS" == "true" ]; then

	strongEcho 'Build Branch ['$TRAVIS_BRANCH']'

	./gradlew release \
	-Dorg.ajoberstar.grgit.auth.username=${GH_TOKEN} \
	-Dorg.ajoberstar.grgit.auth.password --info --stacktrace || EXIT_STATUS=$?

else
    strongEcho 'Build, no analysis => Branch ['$TRAVIS_BRANCH'] Pull Request ['$TRAVIS_PULL_REQUEST']'
    # Build branch, without any analysis
    ./gradlew build check || EXIT_STATUS=$?
fi



if [[ $EXIT_STATUS -eq 0 ]] ; then

	if [ "$TRAVIS_BRANCH" == "master" ] || [ "$TRAVIS_BRANCH" == "develop" ] || [[ $TRAVIS_BRANCH =~ ^(release[-\/])[0-9]+(\.[0-9]+)?\.x$ ]]; then

	strongEcho 'VERSION '$VERSION' with SonarQube'

		if [[ $TRAVIS_BRANCH =~ ^(release[-\/])[0-9]+(\.[0-9]+)?\.x$ ]]; then

		strongEcho 'Analyze Branch '$TRAVIS_BRANCH' with SonarQube'

#		./gradlew sonarqube --info --stacktrace \
#			-Dsonar.branch="release/$VERSION" \
#			-Dsonar.host.url=$SONAR_HOST_URL \
#    		-Dsonar.login=$SONAR_LOGIN || EXIT_STATUS=$?

     	strongEcho "Successful"
		else

		strongEcho 'Analyze Branch '$TRAVIS_BRANCH' with SonarQube'
#
#		./gradlew sonarqube --info --stacktrace \
#			-Dsonar.branch=$TRAVIS_BRANCH \
#			-Dsonar.host.url=$SONAR_HOST_URL \
#    		-Dsonar.login=$SONAR_LOGIN || EXIT_STATUS=$?
#
#     		strongEcho "Successful"

		fi


	fi

fi

exit $EXIT_STATUS
