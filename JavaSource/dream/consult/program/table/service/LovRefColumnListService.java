package dream.consult.program.table.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dto.LovRefColumnListDTO;

/**
 * Ref테이블 팝업 Service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovRefColumnListService
{

    /**
     * 테이블 검색
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
     * 작업그룹 검색
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