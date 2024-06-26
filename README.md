# BookBuddy: a Comprehensive Reading Management Tool

## What will the application do?

**BookBuddy** is an interactive Java application designed for book enthusiasts. This application helps users organize 
their book collections, record their reading experiences, receive personalized book recommendations, and track their 
reading habits and goals. 

### Key features:

**Virtual Bookshelf** - Allows users to organize and manage their personal book collections digitally. Users can add 
books, categorize them, and mark them as read, unread, or in-progress with additional details like author, genre,
length, and more.

**Interactive Reading Journal** - Offers a space for users to write notes, thoughts, and capture favorite quotes for 
each book in their collection. This feature encourages reflective reading and serves as useful log of the user's 
reading experiences.

**Book Recommendation System** - Using a series of user-inputted preferences (such as genre, complexity, and length), 
they will receive suggestions for their next read from their collection.

**Reading Habit & Goal Tracker** - Helps users track their daily reading progress (in terms of pages or books) and set 
specific reading goals. Daily reading streaks will be tracked to motivate consistent reading habits and completion of
personalized goals. This feature will be updated in the future to include visual data representations to illustrate 
the user's progress over time.

Users will start by adding books to their virtual bookshelf. From there, they can create journal entries for each book
that document their reading experiences and encourage reflective reading habits. The reading tracker feature will 
allow for users to log their daily reading activities and set personal reading goals they hope to reach. The 
recommendation feature suggests books from the user's collection based on their preferences for their next read.

## Who will use it?

BookBuddy is designed for avid readers and lovers of literature who want to organize their reading in a digital space.
It is particularly appealing for those who, like myself, have a bad habit of buying new books more frequently than they
end up actually reading, resulting in a large collection of books waiting to be read. This application is also great for 
individuals wanting to track their reading habits and goals and reflect on their reading experiences, members of book
clubs who want to organize their reading lists and prepare notes for discussions, students needing to organize and 
keep track of their assigned readings, and lastly, anyone who is looking to introduce or improve the habit of reading 
into their lives.

## Why did I create this project?

As someone who enjoys reading, the idea of creating an application centered on enhancing the reading experience is both
exciting and personally useful. As a person with ADHD, I struggle with completing books, reading consistently, and 
buying books that end up collecting dust on my shelves as I promise myself "I'll get to them eventually." This has been
particularly frustrating for me, as I truly am very passionate about literature and love to read, I just need a way to
make the habit of reading more structured and goal-oriented. By being able to set goals for how much I would like to
read daily and how many books I would like to finish in a year, I am better able to introduce an effective, consistent
reading habit in my life. Additionally, I often find myself passively reading and forgetting any thoughts or experiences
I had while reading, and by having an interface that includes the ability to jot down any ideas or memorable quotes as
I read would be very beneficial. Aside from the personal benefit I would receive from using such an application, 
developing this project would serve as a challenging opportunity to utilize my skills in software development, UX/UI 
design, and data science.

****

## User Stories

- As a user, I want to be able to add a book to my virtual bookshelf and specify the title, author, genre, and number 
of pages, so that I can organize my reading collection digitally.


- As a user, I want to be able to view a list of titles in my book collection.


- As a user, I want to be able to log my daily reading progress by noting down the amount of pages read each day, and 
view a summary of my progress towards my reading goals to track my reading habits.


- As a user, I want to be able to add journal entries for each book in my collection, specifying my thoughts, ratings, and
quotes.


- As a user, I want to answer questions about my current mood, genre, and length preferences to receive personalized
book recommendations from my collection to help me decide what to read next.

- As a user, I want to be presented the option to save my virtual bookshelf and any modifications I have made to file before quitting the application.

- As a user, when I start the application, I want to have the option to load my virtual bookshelf from file.

****

## Useage Examples

- **Adding a Book to the Virtual Bookshelf:** You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the "Access Bookshelf" button on the home panel, then clicking on the "Add Book" button. This will prompt you to enter details for the new book (title, author, genre, page count). After entering the details, you will have the option to add another book or return to the bookshelf or home panel.
- **Viewing the Bookshelf:** You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the "View Bookshelf" button after adding books in the bookshelf panel. This action displays the virtual bookshelf with all the added books, allowing you to see the titles, authors, genres, and page counts in a table format.
- **Visual Component:** You can locate my visual component by clicking on the "Access Bookshelf" button on the home panel, then clicking on the "View Goal Summary" button. This will display a view detailing the current progress of the current goal set, as well as the visual component of a progress bar denoting the percentage of completion the user is at given the current progress state. 
- **Saving the State of the Application:** You can save the state of the application by closing the window. Upon attempting to exit, a dialog box will appear with options to save changes, discard changes, or cancel the action. Selecting "Save changes" will write the current state of the bookshelf to the JSON file located at `./data/bookshelf.json`.
- **Loading the State of the Application from File:** You can load the state of the application by starting the application and clicking on the "Load Bookshelf" button on the main menu. This will load the bookshelf's state from the previously saved JSON file. This will load the books and their details, reading goals, journal entries, and reading progress that was previously saved on the file.

****

## Reflection

If I had more time to work on this project, I would find ways to improve the design of my ui package. In particular, 
the `MainFrame` class could be refactored to be more modular and less cluttered. My main goal would be to separate the
functionality of the main frame into smaller, more manageable classes that each handle a specific aspect of the
application. This would make the codebase more organized and easier to maintain in the long run. Additionally, I would
like to improve the visual design of the application by incorporating more visual elements to make the application more 
engaging and user-friendly. 

To further improve the design of this application, I would modify the save and load functionality to 
include a better-implemented save/load system that allows for more flexibility in saving and loading the state of the 
application, particularly allowing users to save and load multiple bookshelves. I would also like to modify the way 
users add books in the GUI, so that if a user chooses to close the book-adding dialog without filling out the fields, the 
dialog box will close without continuing to prompt the user to enter the remaining information. This would make the 
application more user-friendly and intuitive to use. I also would love to improve the Reading Goal Tracker feature by 
adding visual data representations to illustrate the user's progress over time. This would include graphs and charts 
that show the user's reading habits and goals in a more visual and interactive way than the currently implemented 
progress bar.
