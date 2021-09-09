Steps to run the api's

Download the projects and add to Local workspace

run EurekaNamingServerCarleaseApplication,CarLeaseServiceApplication
and CarLeaseCompanyServiceApplication as java application

The services are registered under eureka naming server 
You can find active services in the link http://localhost:8761

once the servers are up the user can create authentication request - post request
A token will be generated . Use same token for all the requests further
http://localhost:8001/authenticate
payload:

{
    "userName":"test@abc.com",
    "password":"Mysore"

}

Below are the request methods created and their url

http://localhost:8001/CarLease/addCustomer
http://localhost:8001/CarLease/removeCustomer/{id}
http://localhost:8001/CarLease/Customers
http://localhost:8001/CarLease/removeCustomer/{id}
http://localhost:8001/CarLease/Customers/{id}/addCarDetails

http://localhost:8100/CarDetailsWithLeaseCalculation/model/{model}/mileage/{mileage}/duration/{duration}

Example payload:

{
	"name":"Adam",
	"street":"Leusden",
	"houseNumber":"23C",
	"zipCode":"3822A",
	"place": "Amersfoort",
	"email": "adam@abc.com",
	"phone":"0683127221",
	"car":	{
			"model":"Toyota-Yaris-Hybrid",
			"mileage": 23000,
			"durationofLease": 40
		}
}

Please note. as of now the results will fetch only for below 5 models as 
I have made them primary key since I am using in memory database.

Toyota-Yaris,Toyota-Yaris-Hybrid,Hyundai-Tucson,Hyundai-Bayon,Suzuki-Swift


Swagger Links
http://localhost:8100/swagger-ui.html

http://localhost:8001/swagger-ui.html

http://localhost:8761


Note: Further enhancements needed.
