# ase-fall-17: Lab of Advanced Software Engineering
Collection of homework for the Advanced Software Engineering course, taught by prof. [Antonio Brogi](http://pages.di.unipi.it/brogi/) and dr. [Stefano Forti](http://pages.di.unipi.it/forti/) for the Master's Degree in Computer Science and Master's Degree in Computer Science and Networking of [University of Pisa](http://www.unipi.it).

## List of homework

Number | Languages/Frameworks/Tools | Description | Submitted | Passed
------ | -------------------------- | ----------- | --------- | ------
**0** | Java | A calculator prototype written in Java and tested with JUnit. | ✓ | ✓
**1** | Java, REST, Spring | A toy Doodle RESTful web service written in Java using the Spring framework. | ✓ | ✓
**2** | XML, OpenESB, WSDL, BPEL, SOAP, REST | A web service that takes an IP address (of a server) as input and returns the current weather in the city in which the server is located, orchestrated from the [IP2Geo Web Service](https://ws.cdyne.com/ip2geo/ip2geo.asmx) and the [OpenWeatherMap](http://openweathermap.org) service with WS-BPEL using the [OpenESB](http://www.open-esb.net) framework. | ✓ | ✓
**3** | XML, OpenESB, WSDL, BPEL, SOAP | A web service that takes an airport code (e.g. PSA) as input and returns the airport's full name and time zone, orchestrated with WS-BPEL (managing faults) from two [WebServiceX](http://www.webservicex.net/new/Home/Index) services using the OpenESB framework. | ✓ | ✓
**4** | BPEL, workflow nets, WoPeD | Modeling of a WS-BPEL process using a workflow net, created with the [WoPeD](http://woped.dhbw-karlsruhe.de/woped/) tool. | ✓ | ?
**5** | Bash, Docker | A Bash script that prints a quote (either passed as argument or generated with a "fortune-telling" program) in [cowthink](https://en.wikipedia.org/wiki/Cowthink), deployed as a Docker image built from a Dockerfile and then run in a Docker container. | ✓ | ✓
**6** | TOSCA, YAML, Docker, TosKer, TosKeriser, Bash | The toy Doodle web service from Homework 1, described with the TOSCA language and deployed in a Docker container using the [TosKer](https://github.com/di-unipi-socc/TosKer) and [TosKeriser](https://github.com/di-unipi-socc/TosKeriser) tools. | ✓ | ?
