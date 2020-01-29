package dream.rcm.funceq.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: RcmFuncEqItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFuncEqItemListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFuncEqItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @param rcmFuncEqItemListDTO
     * @return List
     */
    public List findItemList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFuncEqItemListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow);

	public String getEquip(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			String multiEquip, String multiAsmb);
	
	public String[] getRcmAsmb(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			String multiEquip, String multiAsmb);

	public int insertRcmEq(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO,
			String rcmEqId, String equipId);

	public int insertDetail(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO,
			String rcmEqId, String asmbId);
	
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int insertRcmAsmb(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO, String rcmEqAsmbId, String rcmEqId, String eqAsmbId);

	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			User user);
}