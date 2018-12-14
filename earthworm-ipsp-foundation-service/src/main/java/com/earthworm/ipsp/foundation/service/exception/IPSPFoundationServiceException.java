package com.earthworm.ipsp.foundation.service.exception;

import com.earthworm.exception.EarthWormException;

/**
 * IPSP Foundation Service Exception
 */
public class IPSPFoundationServiceException extends EarthWormException {
    public static enum FoundationCode {
        APPMENU_ADD_ERR,
        APPMENU_UPT_ERR,
        APPMENU_CASCDEL_ERR,
        INWAREHOUSE_ADD_ERR,
        INWAREHOUSE_UPT_ERR,
        INWAREHOUSE_DEL_ERR,
        APPSYS_ADD_ERR,
        APPSYS_UPT_ERR,
        APPSYSMENU_ADD_ERR
    }

    public IPSPFoundationServiceException(String errMsg, FoundationCode errCode) {
        super(errMsg, errCode.toString());
    }

    public IPSPFoundationServiceException(Throwable cause, String errMsg, FoundationCode errCode) {
        super(cause, errMsg, errCode.toString());
    }
}
