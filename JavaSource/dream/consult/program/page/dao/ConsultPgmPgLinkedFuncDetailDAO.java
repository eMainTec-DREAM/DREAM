package dream.consult.program.page.dao;

import common.bean.User;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능 상세 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface ConsultPgmPgLinkedFuncDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     */
    public ConsultPgmPgLinkedFuncDetailDTO findDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, User user);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmPgLinkedFuncDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmPgLinkedFuncDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
}