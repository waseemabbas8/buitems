package com.work.buitems_event_guide.api;

import com.work.buitems_event_guide.model.AddEventResponse;
import com.work.buitems_event_guide.model.EventsResponse;
import com.work.buitems_event_guide.model.LoginResponse;
import com.work.buitems_event_guide.model.SearchEventResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface BuitemsApi {

    @GET("login")
    Call<LoginResponse> loginUser(@Query("username") String userName,
                                  @Query("pass") String password);

    @Multipart
    @POST("add_event")
    Call<AddEventResponse> addEvent(@Part("name") RequestBody name,
                                    @Part("guest") RequestBody guest,
                                    @Part("location") RequestBody location,
                                    @Part("date") RequestBody date,
                                    @Part("lat") RequestBody lat,
                                    @Part("long") RequestBody longitude);

    @Multipart
    @POST("update_event")
    Call<AddEventResponse> updateEvent(@Part("id") RequestBody id,
                                       @Part("name") RequestBody name,
                                       @Part("guest") RequestBody guest,
                                       @Part("location") RequestBody location,
                                       @Part("date") RequestBody date,
                                       @Part("lat") RequestBody lat,
                                       @Part("long") RequestBody longitude);

    @GET("search_event")
    Call<SearchEventResponse> searchEvent(@Query("name") String name,
                                          @Query("date") String date);

    @GET("event_data")
    Call<EventsResponse> getAllEvents();

}
