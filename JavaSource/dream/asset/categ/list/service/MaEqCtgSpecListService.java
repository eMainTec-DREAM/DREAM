package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * 설비종류별 표준제원  목록
 * @author  syyang
 * @version $Id: MaEqCtgSpecListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since   1.0
 */
public interface MaEqCtgSpecListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id: MaEqCtgSpecListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgSpecListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findSpecList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User loginUser);
    /**
     *  delete
     * @author  syyang
     * @version $Id: MaEqCtgSpecListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteSpecList(String[] deleteRows, String compNo) throws Exception;

    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgSpecListDTO
     * @return
     */
    public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user) throws Exception;
}
