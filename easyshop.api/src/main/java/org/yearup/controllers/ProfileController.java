package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.security.SecurityUtils;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    private ProfileDao profileDao;
    private UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }


    //When creating the profile controller I thought about authentication so I
    // searched for classes dealing with security and authentication since we do
    //a lot of authenticating  and working with profiles in Postman. I saw that the SecurityUtils class was
    //an instance of userDetails, something I used throughout the classes that I touched dealing with the profile.
    // so I explored how to use it

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Profile getProfile() {
        try {
            String username = SecurityUtils.getCurrentUsername().orElse(null);

            if (username == null) {
                throw new RuntimeException("User is not logged in.");
            }

            User user = userDao.getByUserName(username);
            return profileDao.getByUserId(user.getId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Your profile in danger girl...");
        }
    }


    @RequestMapping(path = "", method = RequestMethod.PUT)
    public Profile updateProfile(@RequestBody Profile profile) {
        try {

            String username = SecurityUtils.getCurrentUsername().orElse(null);

            if (username == null) {
                throw new RuntimeException("User is not logged in.");
            }

            User user = userDao.getByUserName(username);

            profile.setUserId(user.getId());
            profileDao.update(profile);

            //proves it works if it is returned
            return profileDao.getByUserId(user.getId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("This profile not updating girl...");
        }
    }

}



