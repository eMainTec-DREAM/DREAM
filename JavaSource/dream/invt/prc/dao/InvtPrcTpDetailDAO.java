package dream.invt.prc.dao;

import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpDetailDTO;

/**
 * 구매절차 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface InvtPrcTpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @return
     */
    public InvtPrcTpDetailDTO findDetail(InvtPrcTpCommonDTO invtPrcTpCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     */
    public int insertDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     */
    public int updateDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO);

    /**
     * detail confirm
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     */
    public int confirmDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO);
}