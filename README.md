# Spoken Time Formatterr

A simple Java library for converting digital time (`hours` / `minutes`) into **spoken time strings** for different locales.

## Assumptions & Design Decisions

- The library converts digital time into spoken time strings.
- Designed as a reusable library for multiple projects/modules.
- Structured to easily support additional languages/locales via new formatters.
- Code is maintainable, extensible, and fully tested for production readiness.
- Use Groovy Spock for simple more readable tests
- Initially used separate integers for hours and minutes, but ideally a single `String` input (e.g., "20:40") could be used — not a major issue, as it can be handled with parsing.

## Cases
I noticed that one example in the PDF (`6:32 -> "six thirty two"`) might not align with typical British spoken time conventions, especially compared to the next example (`7:35 -> "twenty five to eight"`).

For my implementation, I assumed the following rules:

- **Special times:** noon, midnight, and full hours (e.g., `x:00` → `"one o'clock"`)
- **Minutes ≤ 30:** expressed as minutes past the hour (e.g., `6:15` → `"quarter past six"`)
- **Minutes > 30:** expressed as minutes to the next hour (e.g., `6:35` → `"twenty five to seven"`)

This approach follows common British colloquial usage for spoken time.

## Project Structure

The project contains **two modules**:

1. **Core module**: Implements the spoken time formatting logic for different locales.
2. **Demo module**: Provides a simple Spring Boot REST controller to test the library and demonstrate usage.

## Requirements
- Java 17+ (tested with OpenJDK 17)
- Maven 3.8+ (for building the library)
- IntelliJ IDEA or another Java IDE (optional, recommended for demo)
 
## Clone and Build
1. Clone the repository:
```bash
git clone https://github.com/jarekkopaczewski/spoken-time-formatter.git
```
2. Open IntelliJ and create a new project from existing source.
3. Import the project as a Maven project.
4. Sync Maven in IntelliJ to download dependencies.
5. Build the project (root one):
   ```bash
   mvn clean install
   ```
   Alternatively, use the Maven GUI in IntelliJ.
6. Run the demo application SpokenTimeFormatterDemoApplication from the demo module.

## Testing the Demo REST API

Once the demo application is running (default on port `8080`), you can test it using `curl`, any HTTP client, or even a browser.
Default and only supported locale is en-GB, so its not required param.

### Example Request with curl

```bash
curl "http://localhost:8080/api/time/spoken?hours=16&minutes=12"
curl "http://localhost:8080/api/time/spoken?hours=16&minutes=12&locale=en-GB"

