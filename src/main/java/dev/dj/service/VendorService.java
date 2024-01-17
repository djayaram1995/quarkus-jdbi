package dev.dj.service;

import dev.dj.dao.JdbiDao;
import dev.dj.model.VendorDto;
import dev.dj.rowmapper.VendorRowMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@AllArgsConstructor
public class VendorService {

    private JdbiDao jdbiDao;

    public List<VendorDto> getVendors() {
        return jdbiDao.runGetWithBeanQuery("sql/selectAllVendors.sql", Collections.emptyMap(), VendorDto.class);
    }

    public List<VendorDto> getVendorsByAddress(String address) {
        Map<String, Object> param = new HashMap<>();
        param.put("address", "%"+address+"%");
        return jdbiDao.runGetQuery("sql/selectVendorsByAddress.sql", param, new VendorRowMapper());
    }

    public void createVendor(VendorDto vendorDto) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", vendorDto.getName());
        param.put("contact", vendorDto.getContact());
        param.put("phone", vendorDto.getPhone());
        param.put("email", vendorDto.getEmail());
        param.put("address", vendorDto.getAddress());
        jdbiDao.runInsertQuery("sql/insertVendor.sql", param);
    }
}
