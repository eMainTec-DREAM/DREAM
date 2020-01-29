/**
 * 
 */
package dream.mgr.ptwh.service;

import java.util.List;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
/**
 * 부품창고 담당자 - List Service
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public interface MgrPtWhEmpListService {
	/**
	 * 부품창고 담당자 LIST
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPtWhEmpList(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
	/**
	 * DELETE 부품창고 담당자 LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePtWhEmpList(String[] deleteRows, User user) throws Exception;
	/**
	 * 부품창고 담당자 LIST COUNT
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
}
