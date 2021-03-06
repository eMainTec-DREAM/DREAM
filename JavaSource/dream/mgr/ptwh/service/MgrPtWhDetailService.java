package dream.mgr.ptwh.service;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhDetailDTO;
/**
 * 부품창고 - Detail Service
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 */
public interface MgrPtWhDetailService
{    
	/**
	 * FIND DETAIL
	 * @param mgrPtWhCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrPtWhDetailDTO findDetail(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception;
    /**
     * UPDATE 부품창고
     * @param mgrPtWhDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhDetail(MgrPtWhDetailDTO mgrPtWhDetailDTO, User user) throws Exception;
}
