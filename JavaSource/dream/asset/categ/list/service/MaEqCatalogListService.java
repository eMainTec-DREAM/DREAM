package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;

/**
 * 설비종류 - 목록 service
 * @author  kim21017
 * @version $Id: MaEqCatalogListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCatalogListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqCatalogListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqCatalogCommonDTO
     * @param excelExport 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqCatalogList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqCatalogListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqCatalog(String[] deleteRows, User user) throws Exception;

}
