package com.a305.travelmaker.global.common.dto;

import com.a305.travelmaker.domain.travel.dto.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DestinationDistanceResponse {

  private Integer destinationId;
  private Point point;
  private double prevDestinationDistance;
}
