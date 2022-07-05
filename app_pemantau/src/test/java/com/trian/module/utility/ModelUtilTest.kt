package com.trian.module.utility

import com.trian.domain.utils.utils.getTodayTimeStamp
import com.trian.domain.models.response.OrderResponse
import com.trian.domain.models.response.StatusOrder
import com.trian.domain.models.response.getStatus
import junit.framework.Assert.assertEquals
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.junit.Test

class ModelUtilTest {

    val order = OrderResponse(
        deleted_schedule=false,
        transaction_id="CXP-MT61a5be8d60c84",
        hospital="RS Telecexup Indonesia",
        doctor_hospital_id=13,
        address="",
        doctor="dr Jimi Maxi Djemi Turangan",
        doctor_slug="dr-Jimi-Maxi-Djemi-Turangan",
        speciality="Umum",
        patient="cocokcocok",
        patient_id=24,
        note="",
        doctor_note="",
        prescription="",
        provisional="",
        date="2021-12-01",
        estimate="08:00-12.00",
        type="call",
        price="100000.00",
        request_reschedule_patient=false,
        request_reschedule_doctor=false,
        status_order=3,
        paid=true,
        refund=false,
        bank_name="",
        account_number="",
        account_name="",
        start="",
        join=null,
        web_link="https://cexup.webex.com/cexup/j.php?MTID=m4558eccba78014cfc9baa70a9b3c40e6",
        payment_token="",
        allowed=true,
        request_access=false,
        payment_url="https://sandbox.doku.com/checkout/link/90688dbaf7ca478c9ad35d0553e105e920210330130300012",
        thumb="https://app.cexup.com/storage/avatars/142/conversions/oZ1AwxrtLmrLL9RZ-thumb.jpg"
    )

    @Test
    fun `get date for meeting`(){
        val getTime = order.estimate.split("-")
        val date= "${order.date} ${getTime[0]}:00"

        val formatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss")
        val dt: DateTime = formatter.parseDateTime(date)
        print("${date.toString()}")
        assertEquals(
            true,
            getTodayTimeStamp() > dt.millis
        )
    }

    @Test
    fun `check Status Order Waiting Payment`(){
        assertEquals(
            StatusOrder.WaitingPayment,
            order.apply { status_order = 2 }.getStatus().status
        )
    }

    @Test
    fun `check Status Order Waiting ForMeeting`(){
        assertEquals(
            StatusOrder.StartMeeting,
            order.apply {
                status_order = 3
            }.getStatus().status
        )

        assertEquals(
            false,
            order.apply {
                status_order = 1
            }.getStatus().status  == StatusOrder.WaitingMeeting
        )
        assertEquals(
            false,
            order.apply {
                status_order = 4
            }.getStatus().status  == StatusOrder.WaitingMeeting
        )

        assertEquals(
            false,
            order.apply {
                status_order = 3
            }.getStatus().status  == StatusOrder.WaitingMeeting
        )
    }

    @Test
    fun `check Status Order Reschedule Doctor Or Patient`(){
        assertEquals(
            StatusOrder.RescheduleByDoctor,
            order.apply {
                status_order = 3
                request_reschedule_doctor = true
                request_reschedule_patient = false
            }.getStatus().status
        )

        assertEquals(
            StatusOrder.RescheduleByPatient,
            order.apply {
                status_order = 3
                request_reschedule_doctor = false
                request_reschedule_patient = true
            }.getStatus().status
        )

        assertEquals(
            false,
            order.apply {
                status_order = 4
                request_reschedule_doctor = true
                request_reschedule_patient = false
            }.getStatus().status == StatusOrder.RescheduleByPatient
        )

        assertEquals(
            false,
            order.apply {
                status_order = 4
                request_reschedule_doctor = false
                request_reschedule_patient = true
            }.getStatus().status == StatusOrder.RescheduleByDoctor
        )
    }

    @Test
    fun `check Status Order Reschedule`(){
        assertEquals(
            StatusOrder.RescheduleByDoctor,
            order.apply {
                status_order = 7
                request_reschedule_doctor = true
            }.getStatus().status
        )
    }

    @Test
    fun `check Status Missed Or Reschedule`(){
        assertEquals(
            StatusOrder.MissedMeeting,
            order.apply {
                status_order = 7
                request_reschedule_doctor = false
                request_reschedule_patient = false
            }.getStatus().status
        )

        assertEquals(
            StatusOrder.RescheduleByDoctor,
            order.apply {
                status_order = 7
                request_reschedule_doctor = true
                request_reschedule_patient = false
            }.getStatus().status
        )

        assertEquals(
            StatusOrder.RescheduleByPatient,
            order.apply {
                status_order = 7
                request_reschedule_doctor = false
                request_reschedule_patient = true
            }.getStatus().status
        )
    }
    @Test
    fun `check Status Order Complete`(){
        assertEquals(
            StatusOrder.Completed,
            order.apply { status_order = 4 }.getStatus().status
        )
    }

    @Test
    fun `check Status Order Canceled`(){
        assertEquals(
            StatusOrder.Canceled,
            order.apply {
                status_order = 9
                paid = false
                refund = true
            }.getStatus().status
        )

        assertEquals(
            StatusOrder.RefundRejected,
            order.apply {
                status_order = 9
                paid = true
                refund = false
            }.getStatus().status
        )

        assertEquals(
            StatusOrder.RefundSuccess,
            order.apply {
                status_order = 9
                paid = true
                refund = true
            }.getStatus().status
        )
    }
}