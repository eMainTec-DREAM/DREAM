package dream.mgr.ptwh.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;

/**
 * 부품창고 - List DAO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public interface MgrPtWhListDAO
{
	/**
	 * FIND LIST
	 * @param mgrPtWhCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPtWhList(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception;
    
    /**
	 * FIND LIST COUNT
	 * @param mgrPtWhCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception;
    
}