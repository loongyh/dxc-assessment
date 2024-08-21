# DXC Technical Assessment

## Bookstore API
You are assisting a tech savvy bookstore owner who is trying to categorize all the books in his store and provide a top-of-the-line method to access the books information. You recommended to the owner that a RESTFUL API is the way to go and offered to design a POC.

## Functional Scope
A book has the following fields:
| Field   | Type/Format     | Description         |
| ------- | --------------- | ------------------- |
| isbn    | string          | ISBN of book        |
| title   | String          | Book title          |
| authors | Array of author | Book Author(s)      |
| year    | number / int32  | Year of publication |
| price   | number / double | Price of Book       |
| genre   | string          | Genre of Book       |

An author has the following fields:
| Field    | Type/Format | Description             |
| -------- | ----------- | ----------------------- |
| name     | string      | Author name             |
| birthday | string/date | Date of birth of Author |

Your API should allow the following operations:
- Add a new book.
- Update book
- Find books by title and/or author (Exact Match)
- Delete book (Restricted permission)

## Notes
- A book is identified uniquely by its ISBN and can have more than 1 author.
- All books info (Book information like title, ISBN, Year of Publication, Author(s), Price, Genre etc.)
must be stored in a database.
- No UI is required, but you are expected to demonstrate the output from each operation.
Handling of errors is expected as well.
- Design your API as a protected resource
- The delete book API should be restricted to only authorized role/user
- Implementation of your API should be done in Java
