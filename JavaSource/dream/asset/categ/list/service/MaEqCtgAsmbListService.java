package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * 설비종류별 작업부위  목록
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCtgAsmbListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqCtgAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgAsmbListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findAsmbList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaEqCtgAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteAsmbList(String[] deleteRows, String compNo) throws Exception;

    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgAsmbListDTO
     * @return
     */
    public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user) throws Exception;
}
