package com.ewingelen.chatter.createChat.domain

import com.google.firebase.FirebaseException

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
class NoSuchUserException : FirebaseException("No such user")