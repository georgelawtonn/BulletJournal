## Section 1: Requirements
- Week View : The program displays a weekly overview using a JavaFX GUI. The view resembles a bullet journal spread,
  showing each day of a 7-day week. The user can name the week, and the days are displayed horizontally.
  The window size is set to a reasonable height and width for most screens.

- Event and Task Creation : The user can create new Events or Tasks. For Events, they can provide a name, description
  (optional), day of the week, start time, and duration. All fields except the description are required.
  For Tasks, they can specify a name, description (optional), day of the week, and completion status (marked as not
  complete by default). The creation of Events and Tasks is accessible through a button on the Week view, opening a
  Dialog to input the details. All created Events and Tasks are displayed on the Week view.

- Commitment Warnings : The user can set a maximum number of events and tasks for each day of the week. These maximums
  can be set and updated within the program. The maximums for events and tasks are persisted in the .bujo file
  associated with the week.

- Persistence : The user can save the data in a Week to a file using JSON encoding with a .bujo extension
  (not a real file format). They can click the "Save" button to persist the data. When the program is opened, the user
  can choose a .bujo file to open and display its contents as a week, similar to the Week view. The program adds
  "Save to File" and "Open File" features for data persistence.

## Section 2: Headlining Features
- Menu Bar & Shortcuts : The program includes a Menu Bar similar to standard application interfaces. It includes the
  following set of commands:
  Create New Event: The shortcut is cmd/ctrl + e.
  Create New Task: The shortcut is cmd/ctrl + t.
  Save: The shortcut is cmd/ctrl + s.
  Open: The shortcut is cmd/ctrl + o.
  New Week: The shortcut is cmd/ctrl + n.
  
- Categories : Each event or task in the program can be categorized. The user has the ability to create a new category
  for the entire Week directly from the GUI. This newly created category is stored as part of the .bujo file format for
  the Week. When creating an event or task, the user can select a category for it. Each event or task can have only one
  category assigned to it. It is not mandatory for all events or tasks to have a category assigned.

## Section 3: Power Ups
- Quotes and notes : The program includes a section in the layout where the user can type and save an inspirational
  quote or general notes for their Week. There is a text field provided for the user to input their quote or note.
  Anything written in this text field is persisted in the .bujo file when the Week is saved. This allows the user to
  keep track of their thoughts and reflections throughout the Week.

- Takesie-backsies : The program provides the user with a straightforward way to delete tasks and events. It is
  designed to be easily discoverable within the user interface. When the user chooses to delete an item, it is
  immediately removed from the GUI, ensuring a seamless experience. Additionally, the deleted tasks and events are also
  removed from the .bujo file when the Week is saved, ensuring that the changes are reflected in the persisted data.

- Filter by Category : The program allows the user to filter Events and Tasks based on a specific category. The user
  can choose a category from the GUI to view only the Events and Tasks associated with that category. This feature is
  presented in the user interface, providing an intuitive way to focus on specific categories of interest.

## Section 4: Quality of Life
- Mind Changes : Users have the ability to edit any aspect of existing Events or Tasks. They can modify the name,
  description, and category of any Event or Task directly within the Week view. This flexibility allows users to make
  changes and updates to their existing commitments, ensuring accurate and up-to-date information within the program.

- Task Search : The program provides users with a search functionality specifically designed for Tasks. Users can type
  in a search query to search for any Task within the program. As the user types, the search results dynamically update
  to show only the Tasks that are relevant to the search query. The searching input can be partial or fuzzy. This feature
  enables users to quickly locate and access specific Tasks based on their search criteria.

## Section 5: Extra Credit
- Splash Screen : When the application is launched, a welcome screen is displayed to the user. The splash screen
  provides a visual introduction and acts as an initial point of interaction. There are two possible scenarios for the
  splash screen: User Click: In this case, the splash screen waits for the user to click before proceeding to show their
  week. Clear instructions are provided on the screen, guiding the user to click and continue. The user interface ensures
  that the instructions are easily visible and understandable.
  Timer: the splash screen automatically proceeds to show the user's week after a certain amount of time.

- Privacy Lock : The program implements a privacy lock feature to enhance the security of the bullet journal. This
  feature allows users to choose whether they want to set up a password for their bullet journal before creating it.
  If they choose to set up a password, they can also save the password securely. When loading an existing journal, the
  program prompts the user to verify the password associated with that journal. The user must provide the correct password
  to unlock and access the contents of the journal.