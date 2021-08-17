# one-internship

# Notes App

UI Part: https://github.com/kohanaya/one-internship-web

Features:

- You can register/login/logout.
- User can create a new note, edit or delete existing note and view a list of already existing notes.
- You can add a category to the note (list of categories are visible to their owner only).
- You can filter notes by category or note text.
- Admin can manage users by changing their passwords or enabling/disable their accounts.


URLs:
- POST /api/signup - registration
- POST /api/login - login
- POST /api/logout - logout
- POST /api/me - returns info about current user

- GET /api/notes - returns list of user notes
- POST /api/notes - creates a new note
- DELETE /api/notes/{id} - delete note by id

- GET /categories - returns list of user categories

- GET /users - returns list of users (admins only)
- POST /users/password - creates new user (registration)
- POST /users/{userId}/enableDisable - enables or disables user account




## UML diagram

![My UML diagram](db/uml.png)