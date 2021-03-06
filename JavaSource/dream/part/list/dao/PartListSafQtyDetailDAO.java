package dream.part.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.list.dto.PartListSafQtyDetailDTO;
import dream.part.list.dto.PartListSafQtyListDTO;

/**
 * 부품창고 보관위치 - Detail DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartListSafQtyDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @author cjscjs9
     * @param partListSafQtyListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public PartListSafQtyDetailDTO findPtWhEmpDetail(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @author cjscjs9
     * @param partListSafQtyDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertPtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @author cjscjs9
     * @param partListSafQtyDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception;

    /**
     * valid empId
     * @author cjscjs9
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public String validEmpNo(PartListSafQtyListDTO partListSafQtyListDTO, PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user);
    
}