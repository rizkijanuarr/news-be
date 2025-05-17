package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.DashboardControllerV1;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.v1.DashboardResponseV1;
import com.kiki.newsbe.services.v1.DashboardServiceV1;
import com.kiki.newsbe.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor
public class DashboardControllerImplV1 implements DashboardControllerV1 {

    private final DashboardServiceV1 dashboardServiceV1;

    @Override
    public ListResponseParameter<DashboardResponseV1> getListDataDashboard() {
        return ResponseHelper.createResponse(dashboardServiceV1.getListDataDashboard());
    }
}
