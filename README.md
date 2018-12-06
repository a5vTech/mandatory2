# Mandatory 2



http://104.197.58.189

# Features

##### Administration worker
  - Create user
  - Handle course sign up requests
  - See all users signed up for specific course
  - Change personal settings

##### Teacher
  - Create course
  - Edit course
  - See all assigned courses
  - Change personal settings

##### Student
  - Sign up for course
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



* [App](http://104.197.58.189)
 
[![N|](https://i.imgur.com/IjCgZpJ.jpg)](http://104.197.58.189)

And of course Dillinger itself is open source with a [public repository][dill]
 on GitHub.

/CODE snippets
```
$ sudo su
$ Jesper Petersen [10:20]
cd stack/apache-tomcat/conf/Catalina/localhost/
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
