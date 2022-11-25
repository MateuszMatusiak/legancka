package com.zam.rks.Dto;

import com.zam.rks.model.MapModel;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Builder
public class EventDto implements Serializable {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private MapModel localization;
}
