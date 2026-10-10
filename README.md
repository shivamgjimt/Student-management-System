# StudentHub — Student Management Website

A responsive, colorful browser-based website adapted from your Java Student Management System.

## Run it
- Open `index.html` in Chrome, Edge, or Firefox.
- Demo login: `admin` / `admin123`.

## Features
- Dashboard with total students, departments, average CGPA, and semesters
- Add, search, update, and delete students
- Validation for duplicate roll number/email, phone number, semester, and CGPA
- Department-wise and semester-wise reports
- **Download Report (CSV)** button in Reports: downloads all student records (including roll number, name, department, semester, email, phone, address, and CGPA) as `Student-Management-Report.csv`
- Responsive layout for desktop and mobile

## Important limitations
This is a front-end demo. Student records are saved in the browser's `localStorage` on that device/browser only. They are not shared between users/devices and are not stored in a secure server database. The demo login is also front-end-only and is not suitable for protecting real student data.

For a production/public deployment with secure login and shared persistent records, connect a server backend and database before using real student information.

## Publish a preview with GitHub Pages
1. Upload `index.html` to a GitHub repository.
2. Open the repository's **Settings → Pages**.
3. Under **Build and deployment**, select **Deploy from a branch**.
4. Select branch `main` and folder `/ (root)`, then Save.
5. Wait for GitHub Pages to publish and open the URL it shows.

GitHub Pages only hosts the front-end demo; it does not add a server database or secure authentication.
