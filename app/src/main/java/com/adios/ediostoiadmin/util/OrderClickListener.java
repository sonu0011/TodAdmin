package com.adios.ediostoiadmin.util;

import com.adios.ediostoiadmin.data.modal.CustomerOrder;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;

public interface OrderClickListener {
    public void Onclick(CustomerOrder order);
}
