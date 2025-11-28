// When the user clicks the button with id="loadBooksBtn"
// run the loadBooks() function
document.getElementById("loadBooksBtn").addEventListener("click", loadBooks);
async function loadBooks() {
    try {
        const res = await fetch("http://localhost:8081/books"); // Send GET request to your Java backend endpoint
        if (!res.ok) { // If server returns 4xx or 5xx, treat it as an error
            throw new Error(`HTTP error! Status: ${res.status}`);
        }
        const books = await res.json(); // Convert JSON response into JavaScript objects
        displayBooks(books); // Call the displayBooks function to render the list
    } catch (err) {
        console.error("Failed to load books", err); // Catch network errors or thrown errors
    }
}
// the displayBooks function is on the next slide 

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
   ISBN: ${book.isbn?.value}<br>
   Format: ${book.format}<br>
   State: ${book.state}
  `;
        // The ISBN has a ? after it because it is an optional field in the Java backend. If the field is null, it will not try to access .value and will instead show "ISBN: undefined".
        // the alternative is to do a conditional check before adding the line for ISBN or use a ternary operator.
        // book.isbn ? book.isbn.value : undefined

        // Add the new element to the page
        container.appendChild(div);
    });
}