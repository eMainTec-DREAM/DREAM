package dream.mgr.ptwh.service;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhEmpDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
/**
 * 부품창고 담당자 - Detail Service
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public interface MgrPtWhEmpDetailService
{    
	/**
	 * 부품창고 담당자 DETAIL
     * @author sy.yang
	 * @param mgrPtWhEmpListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrPtWhEmpDetailDTO findPtWhEmpDetail(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
	/**
	 * INSERT 부품창고 담당자 DETAIL
     * @author sy.yang
	 * @param mgrPtWhEmpDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception;
    /**
     * UPDATE 부품창고 담당자 DETAIL
     * @author sy.yang
     * @param mgrPtWhEmpDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo 사원 번호 
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
