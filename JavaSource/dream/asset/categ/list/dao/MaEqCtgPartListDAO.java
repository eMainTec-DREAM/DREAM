package dream.asset.categ.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;



/**
 * 설비종류별 부품 목록 dao
 * @author  kim21017
 * @version $Id: MaEqCtgPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCtgPartListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqCtgPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return List
     */
    public List findPartList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgPartListDTO maEqCtgPartListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqCtgPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo);
    /**
     * FIND TOTAL LIST
     * @param maEqCtgPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MaEqCtgPartListDTO maEqCtgPartListDTO, User user) throws Exception;
}