# treat-yourself-api
This capstone is an e-commerce application EasyShop that I've rebranded the name as treat-yourself-api. This online store will allow you to shop around for items you don't need, but know you want.
---

## 🖥️ Features Overview
---
# ✅ Phase 1: Categories

- View all product categories

🔒 Admin-only category management:
- Add new categories
- Update existing categories
- Delete categories

 🚫 Regular users cannot modify categories (403 Forbidden enforced)

# ✅ Phase 2: Product Search & Bug Fixes

- Search and filter products by category and color

- 🐛 Find, fix, and test bugs

## 🎯 Optional Stretch Goal: Profile Management

- Create user proiles
- View user profiles
- Edit user profiles

## 🔑 Interesting Code

@RequestMapping(path = "", method = RequestMethod.GET)
public Profile getProfile() {
    String username = SecurityUtils.getCurrentUsername().orElse(null);
    if (username == null) {
        throw new RuntimeException("User is not logged in.");
    }
    User user = userDao.getByUserName(username);
    return profileDao.getByUserId(user.getId());
}

Why It's Interesting? 
 When creating the profile controller I thought about authentication so I searched for classes dealing with security and authentication since we do a lot of authenticating and working with profiles in Postman. I saw that the SecurityUtils class was an instance of userDetails, something I used throughout the classes that I touched dealing with the profile so I explored how to use it.

📸 Application Screenshots

🏷️ Categories Loading on Home Page



🗄️ Postman - Admin Creates Category



👥 Postman - User Profile Retrieval



✏️ Postman - User Profile Update



🔒 Postman - 403 Forbidden for Non-Admin Category Creation



### How to Run the Project

Start MySQL and ensure the easyshop schema exists.

Run the Spring Boot backend.

Launch the UI frontend.

Use Postman to test API endpoints.

Explore the application via the browser.
