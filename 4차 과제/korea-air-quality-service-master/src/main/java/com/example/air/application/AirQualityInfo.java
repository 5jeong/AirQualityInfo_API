package com.example.air.application;

import com.example.air.application.util.AirQualityGradeUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class AirQualityInfo {
    private String sido;
    private Double sidoPm10Avg;
    private String sidoPm10AvgGrade;
    private String measureTime;
    private List<GuAirQualityInfo> guList;

    public AirQualityInfo searchByGu(String gu){
        if(gu == null) {
            return this;
        }
        var searchGuInfo = searchGuAirQualtyInfo(gu);
        var searchGu = Collections.singletonList(searchGuInfo);
        guList = Collections.singletonList(searchGuInfo);
        return AirQualityInfo.builder()
                .sido(this.sido)
                .sidoPm10Avg(this.sidoPm10Avg)
                .sidoPm10AvgGrade(this.sidoPm10AvgGrade)
                .guList(guList)
                .build();
    }
    public GuAirQualityInfo searchGuAirQualtyInfo(String gu){
        return guList.stream()
                .filter(guAirQualityInfo -> guAirQualityInfo.getGu().equals(gu))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(gu + "에 해당하는 자치구가 존재하지 않습니다."));
    }
    @Getter
    public static class GuAirQualityInfo {
        // TODO: 자치구 대기질 정보 명세서대로 파라미터 정의
        //private String area;
        private final String gu;
        private final Integer pm10;
        private final Integer pm25;
        private final Double o3;
        private final Double no2;
        private final Double co;
        private final Double so2;
        private final String pm10Grade;
        private final String pm25Grade;
        private final String o3Grade;
        private final String no2Grade;
        private final String coGrade;
        private final String so2Grade;

        public GuAirQualityInfo(String gu, Integer pm10, Integer pm25, Double o3, Double no2, Double co, Double so2) {
            this.gu = gu;
            this.pm10 = pm10;
            this.pm25 = pm25;
            this.o3 = o3;
            this.no2 = no2;
            this.co = co;
            this.so2 = so2;
            this.pm10Grade = AirQualityGradeUtil.getPm10Grade(Double.valueOf(pm10));
            this.pm25Grade = AirQualityGradeUtil.getPm25Grade(Double.valueOf(pm25));
            this.o3Grade = AirQualityGradeUtil.getO3Grade(o3);
            this.no2Grade = AirQualityGradeUtil.getNo2Grade(no2);
            this.coGrade = AirQualityGradeUtil.getCoGrade(co);
            this.so2Grade = AirQualityGradeUtil.getSo2Grade(so2);
        }
    }
}
