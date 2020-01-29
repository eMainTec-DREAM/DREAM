package dream.invt.prc.service;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemDetailDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;

/**
 * 구매절차 item detail service
 * @author  hyosung
 * @version $Id: InvtPrcTpItemDetailService.java,v 1.0 2015/12/04 09:08:29 hyosung Exp $
 * @since   1.0
 */
public interface InvtPrcTpItemDetailService
{    
    /**
     * detail find
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemListDTO
     * @param invtPrcTpCommonDTO
     * @return
     * @throws Exception
     */
    public InvtPrcTpItemDetailDTO findDetail(InvtPrcTpItemListDTO invtPrcTpItemListDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO,User user)throws Exception;
    /**
     * detail update
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailDTO
     * @param invtPrcTpCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailDTO
     * @param invtPrcTpCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO, InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user) throws Exception;
}
