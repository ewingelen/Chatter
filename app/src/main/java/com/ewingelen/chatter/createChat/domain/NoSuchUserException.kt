package com.ewingelen.chatter.createChat.domain

import com.google.firebase.FirebaseException


class NoSuchUserException : FirebaseException("No such user")