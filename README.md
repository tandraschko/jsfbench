# jsfbench

Inspired by https://github.com/lu4242/performance-comparison-java-web-frameworks
I took the JSF application and ported it to CDI and created profiles for MyFaces2.3, MyFaces3.0 and Mojarra 2.3.

## How to run it? (Tested on Java8)

### Configure Tomcat
1) download Tomcat 9.0.16+
2) extract it 3 times (tomcat-myfaces-23, tomcat-myfaces-30, tomcat-mojarra-23)
3) copy the configs from ./tomcat/conf to all 3 instances
   The configs disables autoDeployment, accessLogValve and session persistence

### Build the application
1) go the ./application
2) mvn clean package -Pmyfaces-23, copy the generated war and extract it to ./tomcat-myfaces-23/webapps/jsfbench
3) mvn clean package -Pmyfaces-30, copy the generated war and extract it to ./tomcat-myfaces-30/webapps/jsfbench
4) mvn clean package -Pmojarra-23, copy the generated war and extract it to ./tomcat-mojarra-23/webapps/jsfbench


