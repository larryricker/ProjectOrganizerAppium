for capabilities in BadWithNames.json BadWithNamesPro.json GoodWithNames.json GoodWithNamesPro.json PMIS-Pro.json PMIS.json ProgressReport.json ProgressReportPro.json ProjectInfo.json ProjectInfoPro.json ProjectOrganizer.json ProjectOrganizerPro.json StatusReport4.json StatusReport4Pro.json
do
echo "${capabilities}"
export TEST_CAPABILITIES="${capabilities}"
gradle -Ptag=ios --info --stacktrace clean test
done
