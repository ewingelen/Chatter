package com.ewingelen.chatter.createChat.data.cloud

import com.google.firebase.FirebaseException


class UserNotFoundException : FirebaseException("User with this phone number not found")