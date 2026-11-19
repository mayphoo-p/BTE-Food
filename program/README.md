# BTE Food Court Management System

A Java Swing desktop application for viewing food-court vouchers and
generating sales reports from JSON data.

## Overview

The application provides a login screen and a main menu with three
options:

-   **Show Voucher** -- select a voucher ID to view its date, time-slot
    information, purchased food items, quantities, item totals, and
    voucher total.
-   **Report By Food** -- choose a date range and a food item to view
    the quantity sold across time-slot codes. Results can be sorted in
    ascending or descending order.
-   **Report By Time Slot Code** -- choose a date range and a time slot
    to view quantities sold for each food ID. Results can be sorted in
    ascending or descending order.

## Technologies

-   Java (project configured for Java 17)
-   Java Swing (desktop GUI)
-   JSON for storing application data
-   Gson 2.8.2
-   JSON.simple 1.1.1
-   JDatePicker 1.3.2

## Project Structure

``` text
program/
├── src/                    # Java source files
├── bte_foods.json           # Food menu data
├── bte_tsc.json             # Time-slot data
├── bte_vouchers.json        # Voucher and sales data
├── food1.jpg                # Image used in the main menu
├── gson-2.8.2.jar           # Gson library
├── json-simple-1.1.1.jar    # JSON.simple library
└── jdatepicker-1.3.2.jar    # Date picker library
```

## Requirements

-   Java Development Kit (JDK) 17 or compatible
-   The three `.jar` dependency files included in the project
-   A desktop environment that supports Java Swing

## Run in Eclipse

1.  Extract the project ZIP file.
2.  Open Eclipse and choose **File → Import → Existing Projects into
    Workspace**.
3.  Select the extracted `program` folder.
4.  Add the included JAR files to the project's Java Build Path:
    -   `gson-2.8.2.jar`
    -   `json-simple-1.1.1.jar`
    -   `jdatepicker-1.3.2.jar`
5.  Ensure the JSON files and `food1.jpg` are in the application's
    working directory.
6.  Open `src/LogIn.java` and run it as a Java Application.

## Run from the Command Line

Run these commands from the project folder. On Windows:

``` bat
mkdir bin
javac -cp ".;gson-2.8.2.jar;json-simple-1.1.1.jar;jdatepicker-1.3.2.jar" -d bin src\*.java
java -cp "bin;.;gson-2.8.2.jar;json-simple-1.1.1.jar;jdatepicker-1.3.2.jar" LogIn
```

On macOS or Linux, replace the semicolons (`;`) in the classpath with
colons (`:`), and use `src/*.java` and `bin` as shown.

## Login

The login check in the source code currently uses:

-   **Username:** `MIA`
-   **Password:** `123`

These are demonstration credentials defined directly in
`src/LogIn.java`. Change them in the source code before using the
application in a real environment.

## Data Files

The application reads its data from JSON files:

-   `bte_foods.json` -- food information
-   `bte_tsc.json` -- time-slot information
-   `bte_vouchers.json` -- voucher and sales records

Keep these files in the working directory when launching the
application. The voucher reader currently looks for a file named
`BTE_Vouchers.json` (capital letters) in `src/ReadJSONVoucher.java`. If
the application cannot load voucher records on a case-sensitive
operating system, rename `bte_vouchers.json` to `BTE_Vouchers.json`, or
update the filename in the source code so both names match.

## Notes

-   The application uses local JSON files; no database server is
    configured in the supplied project.
-   The project includes a `food1.jpg` image used by the main menu.
-   The Eclipse project configuration contains machine-specific library
    paths. Reconfigure the JAR references on another computer as
    described above.

## Author

May Phoo Paing, Final Project, INet College.
