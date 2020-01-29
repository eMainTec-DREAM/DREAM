package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;

/**
 * 설비종류별 부품  목록
 * @author  kim21017
 * @version $Id: MaEqCtgPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCtgPartListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqCtgPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @throws Exception
     */
    public List findPartList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgPartListDTO maEqCtgPartListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaEqCtgPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deletePartList(String[] deleteRows, String compNo) throws Exception;
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqCtgPartListDTO
     * @return
     */
    public String findTotalCount(MaEqCtgPartListDTO maEqCtgPartListDTO, User user) throws Exception;
    public int insertPartList(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception;
}
