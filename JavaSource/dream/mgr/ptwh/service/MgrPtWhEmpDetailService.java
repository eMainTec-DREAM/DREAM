package dream.mgr.ptwh.service;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhEmpDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
/**
 * ��ǰâ�� ����� - Detail Service
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public interface MgrPtWhEmpDetailService
{    
	/**
	 * ��ǰâ�� ����� DETAIL
     * @author sy.yang
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrPtWhEmpDetailDTO findPtWhEmpDetail(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
	/**
	 * INSERT ��ǰâ�� ����� DETAIL
     * @author sy.yang
	 * @param mgrPtWhEmpDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception;
    /**
     * UPDATE ��ǰâ�� ����� DETAIL
     * @author sy.yang
     * @param mgrPtWhEmpDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo ��� ��ȣ 
     * @author sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrPtWhEmpDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String validEmpNo(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception;
}
