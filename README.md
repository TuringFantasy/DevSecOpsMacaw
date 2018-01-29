# DevSecOpsMacaw
# USCIS DEVSECOPs Demo Application

This document describes the architecture and technical details for USCIS DEVSECOPs demo application
and contains links to all the relevant technologies.

## 1. Application Access

Here are the important links to access the demo application deployed on AWS.

```
The Image Upload Web site can be accessed at https://ec2-35-168-116-172.compute01.amazonaws.com/image-manager/
The Image Approval Web site can be accessed at https://ec2-35-168-116-172.compute-1.amazonaws.com/image-manager/list.html
The Macaw console can be accessed at https://ec2-35-168-116-172.compute-1.amazonaws.com/console-ui/login.html
```
## 2. Architecture

This demo application is a Microservices based cloud native application developed using Macaw
Microservices Framework. Macaw is an enterprise grade Microservices platform for developing large
scale enterprise cloud native applications. More information about Macaw can be obtained at
https://macaw.io/index.html

The following diagram shows the architecture of Demo Application along with Macaw Components
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/USCIS%20DEVSECOPs%20Demo%20Architecture.jpg)

The demo application was built using the following technologies.

```
 Macaw Microservices Framework (https://macaw.io)
 Deployed using Kubernetes as Container orchestration platform (https://kubernetes.io/)
 Docker containers for containerizing Microservices (https://www.docker.com/)
 Cassandra as Database (http://cassandra.apache.org/)
 Minio as Object Storage (https://www.minio.io/)
 Microservices were developed using Java
 The UI interface was developed using AngularJS (https://angularjs.org/)
```
## 3. Browsing Application Artifacts

Macaw DevOps Console provides ability to manage the life cycle of any Macaw Microservices based
applications. Using Macaw DevOps Console, the developers/admins can deploy new services, view
deployed services, view logs, and access the service APIs etc.

First users need to login into Macaw Console
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/macawlogin.jpg)

Once they login they will be see all platform services and user services and their status in the dashboard
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/dashboard.jpg)

They can click on image-manager-service from the Service Clusters Report (users need to scroll down in
the dashboard to see this report) to get the dashboard for the Image Manager Service
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/clusterreport.jpg)

The Service Dashboard allows users to manage life cycle of the service. It allows users to provision new
instances, de-provision existing instances, browse APIs, view logs etc.
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/devopsconsole.jpg)

They can view all available options by clicking on the menu in the Service Instances report. Since we
have deployed only once instance, there would be only one row in this report. For example users can
see the Docker Container details by selecting “View Docker Info” menu item

The complete Docker info along with the container logs are shows
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/details.jpg)

## 4. Browsing APIs

The APIs of Macaw Services can be browsed using Macaw DevOps Console. They can also be accessed
using REST APIs by interacting with API Gateway. The Macaw API Browser can be invoked by selecting
“Browse APIs” menu item from the service dashboard
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/browseapi.jpg)

For the demo service we can browse all available APIs using the API Browser
![alt text](https://github.com/ZolonTech/DevSecOpsMacaw/blob/master/browseapi2.jpg)

For example if “getCustomers” API is selected (and clock on **Post>>** ) , then all available customers are
shown (as JSON output) on the right side of the window.

Additional Macaw DevOps Console documentation can be found at

https://www.macaw.io/documentation/#devconsole


