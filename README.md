# NewsFlash - Your Daily Dose of News

NewsFlash is a modern Android application that fetches and displays the latest news articles from the News API. Built with Kotlin and Jetpack Compose, NewsFlash provides a seamless and engaging news reading experience. Users can browse through various news categories, search for specific articles, and bookmark their favorite articles for later reading. The app is designed with a clean architecture pattern, ensuring a maintainable and scalable codebase.

## Features

- **Latest News:** Fetches and displays the latest news articles from the News API.
- **Categories:** Browse news articles by different categories (e.g., technology, sports, health).
- **Search Functionality:** Search for specific news articles based on keywords.
- **Bookmarks:** Add articles to your bookmarks for easy access later.
- **Detailed View:** View detailed information about a news article, including the full content.

## Architecture

NewsFlash follows the clean architecture pattern with the MVVM (Model-View-ViewModel) design, ensuring a robust and maintainable codebase:

- **Model:** Handles the data layer and business logic.
- **View:** Manages the UI components and user interactions using Jetpack Compose.
- **ViewModel:** Acts as a bridge between the Model and the View, handling UI-related data and business logic.

## Technologies Used

- **Kotlin:** Utilized for its modern features and conciseness in Android development.
- **Jetpack Compose:** Used for building a reactive and efficient UI.
- **Room:** Integrated for local storage to save articles to the bookmarks.
- **Retrofit:** Used for API communication with the News API.
- **Coroutines:** Implemented for asynchronous programming, ensuring a smooth user experience.

## Usage

- **Browsing News:** Navigate through the latest news articles and different categories.
- **Searching News:** Use the search feature to find articles by keywords.
- **Bookmarking Articles:** Bookmark your favorite articles for later reading.
- **Viewing Details:** Click on an article to view its detailed information.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Special thanks to [News API](https://newsapi.org/) for providing access to a comprehensive collection of news articles.
- Thanks to the Android development community for their contributions and support.
- Inspired by clean architecture principles to create a maintainable and scalable application.

---

Stay informed with NewsFlash! Get the latest news, bookmark your favorite articles, and enjoy a smooth and intuitive news reading experience.

