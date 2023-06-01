package com.ewingelen.chatter.createChat.data.cloud

import com.google.firebase.FirebaseException

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
class UserNotFoundException : FirebaseException("User with this phone number not found")