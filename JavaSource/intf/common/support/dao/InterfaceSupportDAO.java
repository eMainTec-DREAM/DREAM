package intf.common.support.dao;

import java.util.List;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.common.support.dto.InterfaceSupportDTO;

/**
 * Interface Supporter
 * @author  ghlee
 * @version $Id$
 * @since   1.0
 */
public interface InterfaceSupportDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * check valid interface
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * @param interfaceSupportDTO
     */
	boolean isExecIntf(InterfaceSupportDTO interfaceSupportDTO);
    
    /**
     * update interface
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * @param interfaceSupportDTO
     */
    int updateInft(InterfaceSupportDTO interfaceSupportDTO);

    /**
     * insert interface log
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * @param interfaceSupportDTO
     */
    int insertIntfLog(InterfaceSupportDTO interfaceSupportDTO);
    /**
     * get Last Success date 
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * @param interfaceSupportDTO
     */
    String getLastSuccessDate(String compNo, String intfNo);
    
    String getLastSuccessTime(String compNo, String intfNo);

    List getAccData(InterfaceSupportDTO interfaceSupportDTO);

    int updateIntfLog(InterfaceSupportDTO interfaceSupportDTO);
}
