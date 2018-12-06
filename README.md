# Mandatory 2




# Features

##### Administration worker
  - Login with credentials and log out
  - Create user
  - Handle course sign up requests
  - See all users signed up for specific course
  - Change personal settings

##### Teacher
  - Login with credentials and log out
  - Create course
  - Edit course
  - See all assigned courses
  - Change personal settings

##### Student
  - Login with credentials and log out
  - Sign up for courses
  - See courses pending for acceptance
  - See all available courses
  - Change personal settings

# Deployment:
  - 
  - 
  - 



> Deployment information
> here
>
> 
> 
> 
> 



### OTHER 



* [Link to app](http://104.197.58.189)
 
[![N|](https://i.imgur.com/IjCgZpJ.jpg)](http://104.197.58.189)


/CODE snippets
```
$ sudo su
$ cd stack/apache-tomcat/conf/Catalina/localhost/
$ nano manager.xml
```
```
<Context privileged="true" antiResourceLocking="false"
        docBase="${catalina.home}/webapps/manager">
   <Valve className="org.apache.catalina.valves.RemoteAddrValve" allow="^.*$" />
</Context>
```
For production environments...

```sh
$ 
$ 
```
