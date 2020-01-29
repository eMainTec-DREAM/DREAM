package dream.invt.prc.dao;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemDetailDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;

/**
 * 구매절차 Item 상세 dao
 * @author  hyosung
 * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 hyosung Exp $
 * @since   1.0
 */
public interface InvtPrcTpItemDetailDAO
{
    /**
     * detail find
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemListDTO
     * @param invtPrcTpCommonDTO
     * @return
     */
    public InvtPrcTpItemDetailDTO findDetail(InvtPrcTpItemListDTO invtPrcTpItemListDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO,User user);

    /**
     * detail update
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailDTO
     * @param invtPrcTpCommonDTO
     * @return
     */
    public int updateDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user);
    
    /**
     * detail insert
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailDTO
     * @param invtPrcTpCommonDTO
     * @return
     */
    public int insertDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user);
}