package com.a305.travelmaker.domain.city.repository;

import com.a305.travelmaker.domain.city.entity.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

  List<City> findByProvinceId(Integer id);

  City findByName(String cityName);
}
