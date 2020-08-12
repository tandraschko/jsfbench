# jsfbench

Inspired by https://github.com/lu4242/performance-comparison-java-web-frameworks

I took the JSF application, ported it to CDI and created profiles for MyFaces 2.3, MyFaces 2.3-next and Mojarra 2.3.
The JMeter config is preconfigured for 100 threads and 200 loops.

Its also quite interessting compared to another case (http://tandraschko.blogspot.com/2019/03/way-to-myfaces-30.html), where the results of Mojarra 2.3 and MyFaces 2.3 were almost the same and MyFaces 2.3-next was ~15% faster as both Mojarra 2.3 and MyFaces 2.3.
The big difference is that this benchmark simulates a real-world application with Hibernate and a bit business logic.

NOTE:
MyFaces and Mojarra are both configured for better performance. It does not test stateless view or MyFaces ViewPooling. There should not be a big difference.

## Results 12-03-2019

|            | Mojarra 2.3 | MyFaces 2.3 | MyFaces 2.3-next |
| --- | --- | --- | --- |
| Average    |        15ms |         8ms |         8ms |
| 90 % Line  |        23ms |        13ms |        12ms |
| Throughput |  2416.9/sec |  3001.6/sec |  3008.7/sec |


## How to run it? 

Currently only tested on JDK 8.

### Configure Tomcat
1) download Tomcat 9.0.16+
2) extract it 3 times (tomcat-myfaces-23, tomcat-myfaces-23next, tomcat-mojarra-23)
3) copy the configs from ./tomcat/conf to all 3 instances, which disables autoDeployment, accessLogValve and session persistence

### Build the application
1) go the ./application
2) mvn clean package -Pmyfaces-23, copy the generated war and extract it to ./tomcat-myfaces-23/webapps/jsfbench
3) mvn clean package -Pmyfaces-23next, copy the generated war and extract it to ./tomcat-myfaces-23next/webapps/jsfbench
4) mvn clean package -Pmojarra-23, copy the generated war and extract it to ./tomcat-mojarra-23/webapps/jsfbench

### Run via JMeter
1) download JMeter 5.1+
2) Increase MaxUserPort if you are on Windows: https://deploymentresearch.com/research/post/532/fix-for-windows-10-exhausted-pool-of-tcp-ip-ports
3) run one of the tomcats (e.g. ./tomcat-myfaces-23/bin/startup.bat)
4) run jmeter (./jmeter/bin/jmeter.bat)
5) open the ./application.jmx via JMeter
6) run it once for a warmup
7) clean the results
8) run again and see the results under Test Plan/Thread Group/Aggregate Grapp


