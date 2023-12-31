package com.example.UserModule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeoIP {
  private String ipAddress;
  private String device;
  private String city;
  private String fullLocation;
  private Double latitude;
  private Double longitude;
}
