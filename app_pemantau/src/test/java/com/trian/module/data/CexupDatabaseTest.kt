package com.trian.module.data


import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject
import javax.inject.Named


@HiltAndroidTest
@Config(application = HiltTestApplication::class,sdk = [29])
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class CexupDatabaseTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    @Named("test_db")
    lateinit var database: CexupDatabase
    @Inject
    lateinit var gson: Gson
    private lateinit var userDao: UserDao
    private lateinit var corporateDao: CorporateDao
    private lateinit var medicalRecordDao: MedicalRecordDao

    @Before
    fun setup(){
        hiltRule.inject()
        userDao = database.userDao()
        corporateDao = database.corporateDao()
        medicalRecordDao = database.medicalRecordDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun `should insert user to local`()  = runBlocking{
        //given
        val user = User(
            id_user = null,
            user_code=idMember,
            user_id="ini userDoctor id",
            type="ini type " ,
            no_type="ini no type" ,
            name="ini name",
            username="ini username",
            gender="ini gender",
            email="ini email",
            phone_number="ini phone" ,
            address="ini address" ,
            thumb="ini thumb"
        )
        userDao.insertPatient(user)
        //when
        val allUsers = userDao.getById(1)
        //then( userDoctor jika default == null maka akan autogenerate)
        user.id_user = 1
        assertEquals(listOf(user), listOf(allUsers))
    }
    @Test
    fun `should insert nurse to local`()= runBlocking {
        //given
        val nurse = Nurse(
            id = null,
            name= "ini nama",
            email="ini email",
            gender="ini gender" ,
            phoneNumber="ini phone number" ,
            type="ini type",
            address="ini adresss",
            ewsStatus = "",
            firstName = "",
            lastName = "",
            status = false,
            userID = "",
            username = ""
        )
        corporateDao.insert(nurse)
        //when
        val allNurse = corporateDao.allNurse()
        //then
        nurse.id = 1
        assertEquals(listOf(nurse),allNurse)
    }

    @Test
    fun `should insert measurement to local`() = runBlocking{
        //given

        medicalRecordDao.medicalRecordTransaction(measurements)
        //when
        val allMeasurement = medicalRecordDao.getAll()
        //then

        assertEquals(measurements,allMeasurement)

    }

    @Test
    fun `should convert bpm model to json or otherwise `(){
        //check

        //given
        val bpm = "122/78" //BloodPressureModel(systole = 122,diastole = 78)
        val bpmToJson =gson.toJson(bpm)
        //when

        //then
        val fromJson = measurements[3].value
        assertEquals(bpm,fromJson)
    }
}
