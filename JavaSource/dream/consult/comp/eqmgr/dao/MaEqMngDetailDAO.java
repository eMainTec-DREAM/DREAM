package dream.consult.comp.eqmgr.dao;


import dream.consult.comp.eqmgr.dto.MaEqMngDetailDTO;

/**
 * 설비담당자변경 - 상세 dao
 * 
 * @author jung712
 * @version $Id: MaEqMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMngDetailDAO
{
     /**
     * 설비 담당자(정) 변경
     * @author kim21017
     * @version $Id: MaEqMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngDetailDTO
     * @return
     */
    public int updateMainManager(MaEqMngDetailDTO maEqMngDetailDTO);
    
    /**
     * 설비 담당자(부) 변경
     * @author kim21017
     * @version $Id: MaEqMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngDetailDTO
     * @return
     */
    public int updateSubManager(MaEqMngDetailDTO maEqMngDetailDTO);
}