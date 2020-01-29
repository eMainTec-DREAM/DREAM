package dream.consult.program.page.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface ConsultPgmPgLinkedFuncListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param consultPgmPgLinkedFuncListDTO
     * @return List
     */
    public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user);
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteFieldList(String id);
    
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user) throws Exception;

}