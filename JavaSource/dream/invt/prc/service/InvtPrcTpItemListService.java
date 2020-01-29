package dream.invt.prc.service;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;

/**
 * 구매절차 Item 목록 service
 * @author  hyosung
 * @version $Id: InvtPrcTpItemListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since   1.0
 */
public interface InvtPrcTpItemListService
{     
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: InvtPrcTpItemListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @param invtPrcTpItemListDTO
     * @throws Exception
     */
    public List findItemList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO,User user);
    
    public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO,User user);
    
    /**
     *  delete
     * @author  hyosung
     * @version $Id: InvtPrcTpItemListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] m_id,User user) throws Exception;

}
