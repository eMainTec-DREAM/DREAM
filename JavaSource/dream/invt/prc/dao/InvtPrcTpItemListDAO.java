package dream.invt.prc.dao;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;

/**
 * 구매절차 Item dao
 * @author  hyosung
 * @version $Id: InvtPrcTpItemListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
 * @since   1.0
 */
public interface InvtPrcTpItemListDAO
{
    /**
     * grid find
     * @author  hyosung
     * @version $Id: InvtPrcTpItemListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @param invtPrcTpItemListDTO
     * @return List
     */
    public List findItemList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO,User user);
    
    public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO,User user);
    
    /**
     * delete
     * @author hyosung
     * @version $Id: InvtPrcTpItemListDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow,User user);
}