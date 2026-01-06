Black Swan
======
Black Swan is an online marketplace for artists with AI-assisted tagging. 

It allows users to register as either an Artist or a Customer: 

If a user is an Artist, they may open their own shop, where they list artworks for sale. While uploading an artwork, the artist may add arbitrary tags to describe the artwork. They also have the option to automatically generate tags based on the contents of their artwork via the Google Cloud Vision API. 

If a user is a Customer, they may browse artworks from different shops and look at specific shops, as well as place orders for specific artworks by fulfilling the order form. 

Requirements
=======
- `JDK 21+`
- `Maven 3.6.0+`
- `PostgreSQL 16+`
- `Angular 21.0.0+`
- A configured Google Cloud account for the AI-assisted tagging functionality (optional)

Installation
======
- Copy the code from the repository with `git clone`
- Configure your own PostgreSQL database username and password in `resources/application.properties`

Running
======
- Run the backend Java service with `./mvnw spring-boot:run`
- Run the frontend Angular with
  - `cd .\final-project-frontend\` 
  -  `ng serve`
- Go to http://localhost:4200/

