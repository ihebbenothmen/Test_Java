# googleScrapper

Explanation of Changes (Mapped to Old Code Structure)

makeHttpRequest:
Replaces the old makeHttpRequest logic with Google search functionality using Jsoup.
Directly constructs the Google search URL based on user input.
Parses the HTML document to extract titles (h3 elements) and links (a[href]) from search results.

File Handling:
The appendToFile method is retained from the original structure, ensuring the scraped results are written to search_results.json.
HTML Stripping:

The stripHtml method is retained in case additional content cleaning is required (though Jsoup handles most of the parsing).

main Method:
Prompting the user for input remains the same as in the original code.
Calls makeHttpRequest to fetch search results and appendToFile to save the results to a file.
Prints appropriate messages to the console for user feedback.

Removed Obfuscation:
Obfuscated methods like decode and obfuscatedFunction have been removed as they are irrelevant to the problem.

Improved Error Handling:
Added specific error messages and stack traces for debugging during HTTP requests or file operations.
