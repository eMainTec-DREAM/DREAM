package dream.mgr.ptwh.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.ptwh.dto.MgrPtWhEmpDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;

/**
 * 부품창고 담당자 - Detail DAO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrPtWhEmpDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @author sy.yang
     * @param mgrPtWhEmpListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrPtWhEmpDetailDTO findPtWhEmpDetail(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @author sy.yang
     * @param mgrPtWhEmpDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertPtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @author sy.yang
     * @param mgrPtWhEmpDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception;

    /**
     * valid empId
     * @author sy.yang
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public String validEmpNo(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user);
    
}