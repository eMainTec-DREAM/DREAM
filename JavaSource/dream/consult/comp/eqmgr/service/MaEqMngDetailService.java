package dream.consult.comp.eqmgr.service;

import dream.consult.comp.eqmgr.dto.MaEqMngDetailDTO;

/**
 * 설비담당자변경 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaEqMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMngDetailService
{    
    /**
     * update Eq Mng
     * @author kim21017
     * @version $Id: MaEqMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateEqMng(MaEqMngDetailDTO maEqMngDetailDTO) throws Exception;
}
