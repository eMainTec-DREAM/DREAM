package dream.consult.program.table.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dto.LovRefColumnListDTO;

/**
 * Ref테이블 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface LovRefColumnListDAO
{
    /**
     * 테이블 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovRefColumnListDTO
     * @return
     */
    public List findWkCtrList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser);
    
    /**
     * 작업그룹 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovRefColumnListDTO
     * @return
     */
    public List findColList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser);
}