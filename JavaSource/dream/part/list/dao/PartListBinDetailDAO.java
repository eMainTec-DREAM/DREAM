package dream.part.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.list.dto.PartListBinDetailDTO;
import dream.part.list.dto.PartListBinListDTO;

/**
 * 부품창고 보관위치 - Detail DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartListBinDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @author cjscjs9
     * @param partListBinListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public PartListBinDetailDTO findPtWhBinDetail(PartListBinListDTO partListBinListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @author cjscjs9
     * @param partListBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertPtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @author cjscjs9
     * @param partListBinDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception;

    /**
     * valid empId
     * @author cjscjs9
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public String validPtBin(PartListBinListDTO partListBinListDTO, PartListBinDetailDTO partListBinDetailDTO, User user);
    
}