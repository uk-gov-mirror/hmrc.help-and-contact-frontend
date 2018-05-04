#!/bin/bash

echo "Applying migration SelfAssessmentTaxReturnCheck"

echo "Adding routes to conf/app.routes"
echo "" >> ../conf/app.routes
echo "GET        /selfAssessmentTaxReturnCheck                       controllers.SelfAssessmentTaxReturnCheckController.onPageLoad()" >> ../conf/app.routes

echo "Adding messages to conf.messages (English)"
echo "" >> ../conf/messages.en
echo "#######################################################" >> ../conf/messages.en
echo "##  SelfAssessmentTaxReturnCheck" >> ../conf/messages.en
echo "#######################################################" >> ../conf/messages.en
echo "selfAssessmentTaxReturnCheck.title = selfAssessmentTaxReturnCheck" >> ../conf/messages.en
echo "selfAssessmentTaxReturnCheck.heading = selfAssessmentTaxReturnCheck" >> ../conf/messages.en

echo "Adding messages to conf.messages (Welsh)"
echo "" >> ../conf/messages.cy
echo "#######################################################" >> ../conf/messages.cy
echo "##  SelfAssessmentTaxReturnCheck" >> ../conf/messages.cy
echo "#######################################################" >> ../conf/messages.cy
echo "selfAssessmentTaxReturnCheck.title = WELSH NEEDED HERE" >> ../conf/messages.cy
echo "selfAssessmentTaxReturnCheck.heading = WELSH NEEDED HERE" >> ../conf/messages.cy

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration SelfAssessmentTaxReturnCheck completed"
