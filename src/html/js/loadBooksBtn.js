document.addEventListener("DOMContentLoaded", function () {
        var loadBooksButton = document.getElementById("loadBooksBtn");
        console.log("books", loadBooksButton); // Log the button element to verify it was found
        var searchButton = document.getElementById("search-button");
        var searchInput = document.getElementById("search-input");
        var CreateBookButton = document.getElementById("CreateBookBtn");
        
    // loads event listeners for the buttons and search input, and also sets up a click listener on the container to handle delete button clicks using event delegation
        if (loadBooksButton) {
            loadBooksButton.addEventListener("click", loadBooks);
            console.log("Load Books button event listener added");
        } else {
            console.error("Button with id 'loadBooksBtn' was not found");
        }
        if (searchButton) {
            searchButton.addEventListener("click", SearchBooks);
            console.log("Search button event listener added");
        } else {
            console.error("Button with id 'search-button' was not found");
        }
        if (searchInput) {
            searchInput.addEventListener("keypress", function (e) {
                if (e.key === "Enter") {
                    SearchBooks();
                }
            })
        }
        if (CreateBookButton) {
            CreateBookButton.addEventListener("click", createBook);
            console.log("Create Book button event listener added");
        }
        
        const container = document.getElementById("booksContainer");
        container.addEventListener("click", async function (e) {

        if (e.target.classList.contains("delete-button")) {
            const isbn = e.target.getAttribute("data-isbn");
            await DeleteBookBtn(isbn);
            e.target.parentElement.remove(); // remove the book card
        }
        
    });


    // loadBooks function sends a GET request to the backend to retrieve the list of books, then calls displayBooks to render them on the page. 
    // It also includes error handling and logging for debugging purposes.

    async function loadBooks() {
        console.log("Loading books..."); // Log a message to indicate the function has started
        try {
            const res = await fetch("http://localhost:8081/books"); // Send GET request to your Java backend endpoint
            console.log("Response status:", res.status); // Log the HTTP status code for debugging
            if (!res.ok) { // If server returns 4xx or 5xx, treat it as an error
                throw new Error(`HTTP error! Status: ${res.status}`);
            }
            const books = await res.json(); // Convert JSON response into JavaScript objects
            displayBooks(books); // Call the displayBooks function to render the list
            console.log("Books loaded:", books); // Log the loaded books for debugging


        } catch (err) {
            // console.error("Failed to load books", err); // Catch network errors or thrown errors
        }
        console.log("Books loaded");
    }

    // displayBooks takes an array of book objects and creates HTML elements for each book, displaying their details and a delete button. It also handles the case where no books are found.

    function displayBooks(books) {
        const container = document.getElementById("booksContainer"); // Grab the <div> that will hold all book cards. The div has id="booksContainer"
        container.innerHTML = ""; // Clear existing content
        if (books.length === 0) { // Handle "no books" case
            container.innerHTML = "<p>No books found.</p>";
            return;
        }
        books.forEach(book => { // Loop through each book returned from the backend
            const div = document.createElement("div"); // Create a new div for each book
            div.className = "book";
            div.classList.add(
                "p-3",
                "bg-white",
                "rounded-md",
                "mb-2.5",
                "border-l-4",
                "border-indigo-600"
            )
            // Fill the HTML using a template literal
            div.innerHTML = `
        <strong>${book.title}</strong><br>
        Author: ${book.author}<br>
        ISBN: ${book.isbn?.value || book.isbn ||"N/A"}<br>
        Format: ${book.format}<br>
        State: ${book.state}<br>
        <button  class="delete-button border-2 border-solid  bg-green-100 hover:bg-green-300 mt-2" data-isbn="${book.isbn?.value || book.isbn}">Delete</button>
        `;
        container.appendChild(div);
        });
    }

    // SearchBooks function retrieves the ISBN from the search input, validates it, and then calls SearchButton to perform the search. It also includes error handling for missing input.
    function SearchBooks() {
        const searchInput = document.getElementById("search-input");
        const isbn = searchInput.value.trim();

        if (!isbn) {
            console.error("No ISBN entered");
            return;
        }
        SearchButton(isbn);
    }

    // SearchButton sends a GET request to the backend with the ISBN to retrieve the specific book, then calls displayBooks to show the result. It also includes error handling for network issues and invalid responses.
    async function SearchButton(isbn) {
        const searchButton = document.getElementById("search-button");
        console.log("Searching for book with ISBN:", isbn); // Log the ISBN being searched for
        try {
            const res = await fetch(`http://localhost:8081/books/${isbn}`);
            if (!res.ok) {
                throw new Error(`HTTP error! Status: ${res.status}`);
            }
            const book = await res.json();
            displayBooks([book]);
            console.log("Book returned from backend:", book);
        } catch (err) {
            console.error("Failed to search book by ISBN", err);
        }
        

    }

    // DeleteBookBtn sends a DELETE request to the backend with the ISBN to delete the specific book, then calls loadBooks to refresh the list. It also includes error handling for network issues and invalid responses.
    async function DeleteBookBtn(isbn) {
        if (!isbn) return console.error("Missing ISBN for delete");

        try {
            const res = await fetch("http://localhost:8081/books", {
                method: "DELETE",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    action: "delete",
                    Isbn: { value: isbn }  // <-- Must match BookIn.ISBN type
                })
            });

            if (!res.ok) throw new Error("Delete Failed");

            const data = await res.json();
            console.log("Deleted:", data);
            loadBooks(); // refresh the book list
        } catch (err) {
            console.error("Failed to delete book", err);
        }
    }



    // createBook function is defined but not used in the current code. It sends a POST request to the backend to create a new book with hardcoded details, and includes error handling for network issues and invalid responses.
    async function createBook() {
        const CreateBookContainer = document.getElementById("CreateBookContainer"); // Grab the <div> that will hold all the entry fields. The div has id="booksContainer"
        CreateBookContainer.innerHTML = ""; // Clear existing content

        const CreateBookDiv = document.createElement("div"); // Create a new div for the form
            CreateBookDiv.className = "CreateBook";
            CreateBookDiv.classList.add(
                "p-3",
                "bg-white",
                "rounded-md",
                "mb-2.5",
                "border-l-4",
                "border-indigo-600"
            )

        CreateBookContainer.addEventListener("click", async function (e) {
            if (e.target.id === "submit-new-book") {
                const bookData = {
                    title: document.getElementById("new-title").value.trim(),
                    author: document.getElementById("new-author").value.trim(),
                    isbn: document.getElementById("new-isbn").value.trim(),
                    format: document.getElementById("new-format"),
                    state: document.getElementById("new-state")
                };   
                await createBookInBackend(bookData);
            }
        });

        CreateBookDiv.innerHTML = `
            <input type="text" id="new-title" placeholder="Title"><br>
            <input type="text" id="new-author" placeholder="Author"><br>
            <input type="text" id="new-isbn" placeholder="ISBN"><br>
            <select>
                <option value="Physical">Physical</option>
                <option value="ebook">E-book</option>
            </select>
            <select>
                <option value="Available">Available</option>
                <option value="Borrowed">Borrowed</option>
            </select>
            <br>
            <button id="submit-new-book" class="border-2 border-solid  bg-green-100 hover:bg-green-300 mt-2">Submit</button>
            `;
        CreateBookContainer.appendChild(CreateBookDiv);

        console.log("Create Book function executed");
        loadBooksButton.addEventListener("click", loadBooks);
        console.log("Load Books button event listener added");


        // if (contains("submit-new-book")) {
        //     addEventListener("click", async function () {
        //         const bookData = {
        //             title: document.getElementById("new-title").value.trim(),
        //             author: document.getElementById("new-author").value.trim(),
        //             isbn: document.getElementById("new-isbn").value.trim(),
        //             format: document.getElementById("new-format").value.trim(),
        //             state: document.getElementById("new-state").value.trim()
        //         };   
        //     });

        // await createBookInBackend(bookData);
        // }
    }

    async function createBookInBackend(bookData) {
        
        try {
            const res = await fetch("http://localhost:8081/books", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    Method: "POST",
                    action: "create",
                    "title": bookData.title,
                    "author": bookData.author,
                    "isbn": { value: bookData.isbn },
                    "format": bookData.format,
                    "state": bookData.state
                })
            });

            if (!res.ok) throw new Error("Create failed");

            const data = await res.json();
            console.log("Created:", data);

            loadBooks(); // refresh list after adding
        } catch (err) {
            console.error("Failed to create book", err);
        }
    }


});
