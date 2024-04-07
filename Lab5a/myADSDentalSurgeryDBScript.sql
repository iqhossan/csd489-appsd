
#Display the list of ALL Dentists registered in the system, sorted in ascending order of their lastNames
Select * from dentists order by lastName asc;

#Display the list of ALL Appointments for a given Dentist by their dentist_Id number. Include in the result, the Patient information.
Select app.*,pat.* from appointments app 
inner join dentists den on app.dentistId = den.dentistId
inner join patients pat on pat.patientId = app.patientId 
Where app.dentistId = 2; 

#Display the list of ALL Appointments that have been scheduled at a Surgery Location
Select app.*,loc.* from appointments app 
inner join suregerylocation loc on app.surgeryLocationId = loc.locationId;

#Display the list of the Appointments booked for a given Patient on a given Date. 
Select app.*,pat.* from appointments app 
inner join patients pat on pat.patientId = app.patientId 
Where app.patientId = 3 and app.appointDate = '2024-07-05';
