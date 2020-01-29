package dream.mgr.ptwh.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;

/**
 * 부품창고 담당자 - List DAO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrPtWhEmpListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPtWhEmpList(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePtWhEmpList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
    
}