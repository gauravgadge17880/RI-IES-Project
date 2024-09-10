package com.user.api.responce;

import lombok.Data;

@Data
public class DashboardResponse {

	private Long planCount;

	private Long citizensApprovedCnt;

	private Long citizensDeniedCnt;

	private Long citizensBenifitCnt;

}
