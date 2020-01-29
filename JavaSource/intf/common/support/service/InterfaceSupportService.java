package intf.common.support.service;

import java.util.List;

import intf.common.support.dto.InterfaceSupportDTO;

/**
 * Interface Supporter
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface InterfaceSupportService
{
    /**
     * check valid interface
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param interfaceSupportDTO
     * @return isValid
     */
    boolean isExecIntf(InterfaceSupportDTO interfaceSupportDTO);
    
    /**
     * log execution data
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param interfaceSupportDTO
     * @return isValid
     */
    int logExeData(InterfaceSupportDTO interfaceSupportDTO);
    /**
     * Make EXEC Log Str
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param interfaceSupportDTO
     * @return execLog
     */
    String makeExecLogStr(Exception ex, String str);
    /**
     * get last success exe date 
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param interfaceSupportDTO
     * @return execLog
     */
    String getLastSuccessDate(String compNo, String intfNo);
    
    String getLastSuccessTime(String compNo, String intfNo);

    List getAccData(InterfaceSupportDTO interfaceSupportDTO);
}
