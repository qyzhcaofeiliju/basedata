package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.FixshelfBillinfo;
import com.earthworm.ipsp.foundation.entity.FixshelfDetailinfo;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/6 23:49
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface IFixshelfBillinfoService {
    List<FixshelfBillinfo> findAll();

    int deleteById(String billId) throws IPSPFoundationServiceException;

    FixshelfBillinfo findById(String billId);

    int update(FixshelfBillinfo fixshelfBillinfo);

    int update(FixshelfBillinfo fixshelfBillinfo, FixshelfDetailinfo fixshelfDetailinfo) throws IPSPFoundationServiceException;

    int save(FixshelfBillinfo fixshelfBillinfo);

    int save(FixshelfBillinfo fixshelfBillinfo, FixshelfDetailinfo fixshelfDetailinfo) throws IPSPFoundationServiceException;

    List<FixshelfBillinfo> find(FixshelfBillinfo fixshelfBillinfo);

    List<FixshelfBillinfo> findLikeInfo(FixshelfBillinfo fixshelfBillinfo);

    long countAll();
}
