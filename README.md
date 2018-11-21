## Dog Breeds

Based on task description.

Application uses AWS S3 to store images and in-memory H2 database to save information about dogs. 

In AWS IAM service user with S3FullAccess should be created and hit credentials should  be setup properly on your computer.
Link to setup on pc: `https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html`


#### Available commands:

	`POST /breed` - creates new random bread
	`GET /breed` - retrieve all available in DB breads
	`GET /breed/{id}` - retrieve bread by ID
	`GET /breed/find?name={name}` - retrieve breads with name like in parameter
	`DELETE /breed/{id}` - removes breed from db by ID

