package dream.asset.loc.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.loc.list.dto.MaEqLocCommonDTO;

/**
 * 설비위치 - 목록 service
 * @author  kim21017
 * @version $Id: MaEqLocListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqLocListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqLocListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqLocCommonDTO
     * @param excelExport 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqLocList(MaEqLocCommonDTO maEqLocCommonDTO, User user, boolean excelExport);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqLocListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param maEqLocCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteEqLoc(String[] deleteRows, MaEqLocCommonDTO maEqLocCommonDTO, User user) throws Exception;
}
