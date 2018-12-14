package com.earthworm.ipsp.foundation.service.interf;


import com.earthworm.ipsp.foundation.entity.SuitcaseProfile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import java.util.List;

public interface ISuitcaseProfileService {
    List<SuitcaseProfile> findAll();

    int deleteById(String id);

    SuitcaseProfile findById(String id);

    int update(SuitcaseProfile suitcaseProfile);

    int save(SuitcaseProfile suitcaseProfile);

    List<SuitcaseProfile> find(SuitcaseProfile suitcaseProfile);

    long countAll();

    List<SuitcaseProfile> prseExcelMethod(MultipartFile file, ServletResponse response);
}
