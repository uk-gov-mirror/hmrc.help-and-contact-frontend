#!/bin/bash

echo "Applying migration HowToPaySelfAssessment"

echo "Adding routes to conf/app.routes"
echo "" >> ../conf/app.routes
echo "GET        /howToPaySelfAssessment                       controllers.HowToPaySelfAssessmentController.onPageLoad()" >> ../conf/app.routes

echo "Adding messages to conf.messages (English)"
echo "" >> ../conf/messages.en
echo "#######################################################" >> ../conf/messages.en
echo "##  HowToPaySelfAssessment" >> ../conf/messages.en
echo "#######################################################" >> ../conf/messages.en
echo "howToPaySelfAssessment.title = howToPaySelfAssessment" >> ../conf/messages.en
echo "howToPaySelfAssessment.heading = howToPaySelfAssessment" >> ../conf/messages.en

echo "Adding messages to conf.messages (Welsh)"
echo "" >> ../conf/messages.cy
echo "#######################################################" >> ../conf/messages.cy
echo "##  HowToPaySelfAssessment" >> ../conf/messages.cy
echo "#######################################################" >> ../conf/messages.cy
echo "howToPaySelfAssessment.title = WELSH NEEDED HERE" >> ../conf/messages.cy
echo "howToPaySelfAssessment.heading = WELSH NEEDED HERE" >> ../conf/messages.cy

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration HowToPaySelfAssessment completed"
