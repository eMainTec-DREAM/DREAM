package dream.consult.program.table.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dto.LovRefColumnListDTO;

/**
 * Ref���̺� �˾� Service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovRefColumnListService
{

    /**
     * ���̺� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovRefColumnListDTO
     * @param loginUser
     * @return
     */
    List findWkCtrList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser);
    
    /**
     * �۾��׷� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovRefColumnListDTO
     * @param loginUser
     * @return
     */
    List findColList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser);
}