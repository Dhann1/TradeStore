# TradeStore
Trade Store Application
This is a trade store application and it primarly supports below functionalities.
1) User can add trade deatils.
2) Validators are in place for validating the input data as per business rules defined. 
3) It has primerly two validator one for validating the trade versions and second one checking expiration of trade details.
4) This application has java timer based scheduled job running every mintue to check to expiration of any trade and it update the store accordingly.
5) This can be easy extended to provide support for new validation needed by business in future. 
6) IT has loosly coupled architecture.This project has built using java8.
7) This is maven java project. to run this user can run below command 
   mvn clean install or this can be imported to IDE to run. 
Note : Update and remove trade is not yet implemented  in this version. 
