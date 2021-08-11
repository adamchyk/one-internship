# one-internship

Features:

- You can register/login/logout.
- User can create a new note, edit or delete existing note and view a list of already existing notes.
- You can add a category to the note (list of categories are visible to user).
- You can filter notes by category.

URLs:
- GET /users - returns list of users (available to admins only)
- POST /users - creates new user (registration)

- GET /notes - returns list of user notes
- POST /notes - creates new note
- DELETE /notes/{Id} - delete note by id

- GET /categories - returns list of user categories
- POST /categories - creates new category
- DELETE /categories/{Id} - delete category by id



## UML diagram

![My UML diagram](db/uml.png)