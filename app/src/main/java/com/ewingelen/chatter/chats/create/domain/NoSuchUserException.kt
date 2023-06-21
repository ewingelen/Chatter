package com.ewingelen.chatter.chats.create.domain

import com.google.firebase.FirebaseException


class NoSuchUserException : FirebaseException("No such user")