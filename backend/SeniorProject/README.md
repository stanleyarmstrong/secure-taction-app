How to set up this spring boot application:
To Run the application
- Run "SeniorProjectApplication.java"
- Settings for the app will be handled in application.yml file.
- Port will be 10180

Make sure to
- Sign in to AWS Educate and update ~/.aws/credentials with aws values
- Access Key
- Secret Key
- Security Token

Have AWS DynamoDB installed locally through a docker image
- https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html
- Docker will automatically run on port 8000
- Well will be running locally, so region will be us-west-2 (reference a xSerivce.java file)

Build / Compile
- In the project root run: mvn clean install
- This will automatically pull any dependencies required based on the pom.xml
- Hypothetically, you shouldn't need to download the AWS SDK because this project should already have it, but make sure to have:
- AWS CLI set up locally with all file paths set up.
- If AWS SDk is causing trouble, go to https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-install.html

Make sure you have your docker image running before trying to run any of the endpoints!