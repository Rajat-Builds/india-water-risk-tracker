package com.rajat.waterrisktracker.service;

import com.rajat.waterrisktracker.entity.DataCenter;
import com.rajat.waterrisktracker.repository.DataCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataCenterService {

    @Autowired
    private DataCenterRepository dataCenterRepository;

    public List<DataCenter> getAllDataCenters() {
        return dataCenterRepository.findAll();
    }

    public DataCenter getDataCenterById(Long id) {
        return dataCenterRepository.findById(id).orElse(null);
    }

    public DataCenter createDataCenter(DataCenter dataCenter) {
        return dataCenterRepository.save(dataCenter);
    }

    public DataCenter updateDataCenter(Long id, DataCenter updatedDataCenter) {
        DataCenter existing = dataCenterRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setName(updatedDataCenter.getName());
        existing.setCompany(updatedDataCenter.getCompany());
        existing.setCity(updatedDataCenter.getCity());
        existing.setDistrict(updatedDataCenter.getDistrict());
        existing.setState(updatedDataCenter.getState());
        existing.setLatitude(updatedDataCenter.getLatitude());
        existing.setLongitude(updatedDataCenter.getLongitude());
        existing.setFacilityScaleMW(updatedDataCenter.getFacilityScaleMW());
        existing.setOperationalStatus(updatedDataCenter.getOperationalStatus());
        return dataCenterRepository.save(existing);
    }

    public boolean deleteDataCenter(Long id) {
        if (!dataCenterRepository.existsById(id)) {
            return false;
        }
        dataCenterRepository.deleteById(id);
        return true;
    }
}