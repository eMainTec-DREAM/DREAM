package dream.mgr.ptwh.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;

/**
 * 부품창고 보관위치 - Detail DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrPtWhBinDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @author cjscjs9
     * @param mgrPtWhBinListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrPtWhBinDetailDTO findPtWhEmpDetail(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @author cjscjs9
     * @param mgrPtWhBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertPtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @author cjscjs9
     * @param mgrPtWhBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception;

    /**
     * valid empId
     * @author cjscjs9
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public String validEmpNo(MgrPtWhBinListDTO mgrPtWhBinListDTO, MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user);
    
}