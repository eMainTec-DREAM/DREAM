package dream.part.pur.buy.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;

/**
 * 备概脚没item 格废
 * @author  kim21017
 * @version $Id: MaPtBuyReqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtBuyReqListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPtBuyReqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @param maPtBuyReqListDTO
     * @throws Exception
     */
    public List findItemList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaPtBuyReqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] ptprlist_id, String[] ptpritem_id, String[] ptpnlist_id, User user) throws Exception;
    public int insertItemList(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception;
    
    /**
     * 泅厘脚没何前
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtBuyReqDetailDTO
     * @param maPtBuyReqHdrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertItemListByPtPn(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception;
    public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user) throws Exception;
    
    public void saveList(List<Map> gridList, User user) throws Exception;
}
