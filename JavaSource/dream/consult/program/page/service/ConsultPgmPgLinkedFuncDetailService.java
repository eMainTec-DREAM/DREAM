package dream.consult.program.page.service;

import common.bean.User;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능 상세
 * @author  kim210117
 * @version $Id$
 * @since   1.0
 */
public interface ConsultPgmPgLinkedFuncDetailService
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
     * @throws Exception
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
     * @throws Exception
     */
    public int updateDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmPgLinkedFuncDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
}
