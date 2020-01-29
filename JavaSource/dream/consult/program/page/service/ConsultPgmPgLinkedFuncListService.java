package dream.consult.program.page.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능  목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface ConsultPgmPgLinkedFuncListService
{
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param consultPgmPgLinkedFuncListDTO
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws Exception
     */
    public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deleteFieldList(String[] deleteRows) throws Exception;
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO, User user) throws Exception;
}
