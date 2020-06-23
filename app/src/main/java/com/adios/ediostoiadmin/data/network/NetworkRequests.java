package com.adios.ediostoiadmin.data.network;


import com.adios.ediostoiadmin.data.modal.AdminSettings;
import com.adios.ediostoiadmin.data.modal.ChangePasswordResponse;
import com.adios.ediostoiadmin.data.modal.GenericResponse;
import com.adios.ediostoiadmin.data.modal.payloads.ChangePasswordPayload;
import com.adios.ediostoiadmin.data.modal.payloads.DeliveryPartnerPayload;
import com.adios.ediostoiadmin.data.modal.payloads.OderSummaryPayload;
import com.adios.ediostoiadmin.data.modal.payloads.SearchOrderPayload;
import com.adios.ediostoiadmin.data.modal.payloads.UpdateOrderPayload;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;
import com.adios.ediostoiadmin.data.modal.payloads.OrderPayload;
import com.adios.ediostoiadmin.data.network.responses.AuthResponse;
import com.adios.ediostoiadmin.data.modal.payloads.LoginPayload;
import com.adios.ediostoiadmin.data.network.responses.DeliveryPartnerResponse;
import com.adios.ediostoiadmin.data.network.responses.OrderSummaryResponse;
import com.adios.ediostoiadmin.data.network.responses.UpdateOrderResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkRequests {

    @POST("adminLogin")
    Call<AuthResponse> userLogin(@Body LoginPayload loginPayload);

    @POST("adminOrdersV2")
    Call<AllOrdersResponse> getAllOrders(@Body OrderPayload orderPayload);

    @POST("orderSummary")
    Call<OrderSummaryResponse> getOrdersSummary(@Body OderSummaryPayload orderPayload);

    @POST("changePassword")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordPayload passwordPayload);

    @POST("fetchDeliveryPartners")
    Call<DeliveryPartnerResponse> getDeliveryPartners(@Body DeliveryPartnerPayload partnerPayload);

    @POST("filterOrders")
    Call<AllOrdersResponse> searchOrders(@Body SearchOrderPayload orderPayload);

    @POST("adminSettingsV3")
    Call<GenericResponse> updateSettings(@Body AdminSettings settings);

    @POST("updateOrderV2")
    Call<UpdateOrderResponse> updateOrder(@Body UpdateOrderPayload orderPayload);

}
