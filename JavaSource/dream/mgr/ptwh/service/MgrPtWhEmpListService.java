/**
 * 
 */
package dream.mgr.ptwh.service;

import java.util.List;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
/**
 * ��ǰâ�� ����� - List Service
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public interface MgrPtWhEmpListService {
	/**
	 * ��ǰâ�� ����� LIST
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPtWhEmpList(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
	/**
	 * DELETE ��ǰâ�� ����� LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePtWhEmpList(String[] deleteRows, User user) throws Exception;
	/**
	 * ��ǰâ�� ����� LIST COUNT
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
}
