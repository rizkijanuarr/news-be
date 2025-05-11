package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.DashboardControllerV1;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.ResponseHelper;
import com.kiki.newsbe.services.v1.DashboardServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@BaseControllerImpl
@RequiredArgsConstructor
public class DashboardControllerImplV1 implements DashboardControllerV1 {

    private final DashboardServiceV1 dashboardServiceV1;

    @Override
    public ResponseEntity<BaseResponse> getListDataDashboard() {
        return ResponseHelper.buildOkResponse(dashboardServiceV1.getListDataDashboard());
    }
}
