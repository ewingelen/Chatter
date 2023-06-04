package com.ewingelen.chatter.createProfile.domain

interface CreateProfileResult {

    class Success: CreateProfileResult {

    }

    class Fail: CreateProfileResult {

    }
}