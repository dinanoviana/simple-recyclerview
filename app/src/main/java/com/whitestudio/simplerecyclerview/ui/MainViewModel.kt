package com.whitestudio.simplerecyclerview.ui

import androidx.lifecycle.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.whitestudio.simplerecyclerview.model.Students
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import com.whitestudio.sidejo.viewmodel.Result

class MainViewModel (private val dispatcher: CoroutineDispatcher) : ViewModel(), LifecycleObserver {
    private val _firestore = Firebase.firestore
    private val _loading = MutableLiveData<Boolean>()
    private val _fetchStudentsListResult = MutableLiveData<Result<ArrayList<Students>>>()
    val loading: LiveData<Boolean> = _loading

    init {
        _loading.postValue(false)
    }

    val fetchStudentsListResult = _fetchStudentsListResult
    fun fetchStudentsList() {
        _loading.postValue(true)
        viewModelScope.launch(dispatcher) {
            try {
                val studentsList = ArrayList<Students>()
                _firestore.collection(Students.COLLECTION_NAME)
                    .get()
                    .addOnSuccessListener {
                        for (document in it.documents) {
                            val students = document.toObject(Students::class.java)
                            students?.let { student ->
                                studentsList.add(student)
                            }
                        }
                        _loading.postValue(false)
                        _fetchStudentsListResult.postValue(Result.Success(studentsList))
                    }
                    .addOnFailureListener { e ->
                        _loading.postValue(false)
                        _fetchStudentsListResult.postValue(Result.Failure("failed with exception: ", e))
                    }
            } catch (e: Exception) {
                _loading.postValue(false)
                _fetchStudentsListResult.postValue(Result.Failure("failed with exception: ", e))
            }
        }
    }
}