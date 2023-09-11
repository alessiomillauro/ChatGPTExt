package com.android.chatgptext.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class OpenAIRepositoryImpl @Inject constructor(
    private var fsInstance: FirebaseFirestore
) : OpenAIRepository {


}