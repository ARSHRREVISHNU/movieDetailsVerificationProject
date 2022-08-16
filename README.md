# movieDetailsVerificationProject

# Project description
This project will take movie name as input from excel file search it in the imdb at first and collect the Country and Release Date fields and then search the movie in wikpedia and collect the Country and Release Date fields and then compare the country and release date in IMDB and Wikipedia for each movie.
As of now 10 movies was passed in the excel file.

# Prerequisites
To run this project Java and Maven to be installed in the system. Please refer this url for the installation process.
https://toolsqa.com/maven/how-to-install-maven-on-windows/
Then the chrome version of 104 was needed.
![image](https://user-images.githubusercontent.com/24726373/184782003-3e8c1e0f-1b81-49ac-ac3e-d84208a64869.png)

#Steps to run
1. Pull this project to your loacl system.
2. Open command prompt from inside the project directory or use "cd (project director path)" command. 
3. Execute the command "mvn clean test -DtestNGFile=testng.xml".
4. Once the execution was completed. Please check the results in the "emailable-report.html" which will be present in the "test-output" folder.
