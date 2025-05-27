package com.example.vkr_rzaevaab.api

import com.example.vkr_rzaevaab.entities.Device
import com.example.vkr_rzaevaab.entities.Event
import com.example.vkr_rzaevaab.entities.DeviceReservation
import com.example.vkr_rzaevaab.entities.EventRegistration
import com.example.vkr_rzaevaab.entities.LoginDto
import com.example.vkr_rzaevaab.entities.Message
import com.example.vkr_rzaevaab.entities.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("users")
    suspend fun createUser(@Body user: User): User

    @PATCH("users/{user_id}")
    suspend fun editUser(@Path("user_id") userId:Int, @Body user: User): User

    @POST("users/login")
    suspend fun login(@Body loginDto: LoginDto): User

    @DELETE("users/{user_id}")
    suspend fun deleteUser(@Path("user_id") userId:Int, @Body user: User): User

    @GET("users/email/{email}")
    suspend fun checkEmail(@Path("email") email: String): Message

    @GET("users")
    suspend fun getAllUsers(): List<User>

    @GET("users/{user_id}")
    suspend fun getUserById(@Path("user_id") userId:Int): User


    @POST("events")
    suspend fun createEvent(@Body event: Event): Event

    @PATCH("events/{event_id}")
    suspend fun editEvent(@Path("event_id") eventId:Int, @Body event: Event): Event

    @DELETE("events/{event_id}")
    suspend fun deleteEvent(@Path("event_id") eventId:Int, @Body event: Event): Event

    @GET("events")
    suspend fun getAllEvents(): List<Event>

    @GET("event/{event_id}")
    suspend fun getEventById(@Path("event_id") eventId:Int): Event


    @POST("devices")
    suspend fun createDevice(@Body device: Device): Device

    @PATCH("devices/{device_id}")
    suspend fun editDevice(@Path("device_id") deviceId:Int, @Body device: Device): Device

    @DELETE("devices/{device_id}")
    suspend fun deleteDevice(@Path("device_id") deviceId:Int, @Body device: Device): Device

    @GET("devices")
    suspend fun getAllDevices(): List<Device>

    @GET("devices/{device_id}")
    suspend fun getDeviceById(@Path("device_id") deviceId:Int): Device


    @POST("device_reservs")
    suspend fun createDeviceReserv(@Body deviceReservation: DeviceReservation): DeviceReservation

    @DELETE("device_reservs/{device_reserv_id}")
    suspend fun deleteDeviceReserv(@Path("device_reserv_id") deviceReservId:Int, @Body  deviceReservation: DeviceReservation): DeviceReservation

    @GET("device_reservs")
    suspend fun getAllDeviceReservs(): List<DeviceReservation>

    @GET("device_reservs/{device_reserv_id}")
    suspend fun getDeviceReservById(@Path("device_reserv_id") deviceReservId:Int): DeviceReservation

    @GET("users/{user_id}/device_reservs")
    suspend fun getDeviceReservByUserId(@Path("user_id") userId:Int): List<DeviceReservation>

    @GET("devices/{device_id}/device_reservs")
    suspend fun getDeviceReservByDeviceId(@Path("device_id") deviceId:Int): List<DeviceReservation>


    @GET("devices/{device_id}/device_reservs/{date}")
    suspend fun getDeviceReservByDeviceIdAndDate(@Path("device_id") deviceId: Int, @Path("date") date:String): List<DeviceReservation>



    @POST("event_regs")
    suspend fun createEventRegistr(@Body eventRegistration: EventRegistration): EventRegistration

    @DELETE("event_regs/{event_reg_id}")
    suspend fun deleteEventRegistr(@Path("event_reg_id") eventRegId:Int, @Body  eventRegistration: EventRegistration): EventRegistration

    @GET("event_regs")
    suspend fun getAllEventRegistrs(): List<EventRegistration>

    @GET("event_regs/{event_reg_id}")
    suspend fun getEventRegitrById(@Path("event_reg_id") eventRegId: Int): EventRegistration

    @GET("users/{user_id}/event_regs")
    suspend fun getEventRegistrByUserId(@Path("user_id") userId:Int): List<EventRegistration>

    @GET("events/{event_id}/event_regs")
    suspend fun getEventRegistrByEventId(@Path("event_id") eventId:Int): List<EventRegistration>

    @GET("events/{event_id}/remaining_seats")
    suspend fun getCountOfRemainingSeats(@Path("event_id") eventId:Int): Int

}