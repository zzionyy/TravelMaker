package com.a305.travelmaker.domain.destination.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DestinationRecommendRequest {

  private Long userId;
  private Integer cityId;
}
