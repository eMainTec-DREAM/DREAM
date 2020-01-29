package dream.asset.rpt.monthnew.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.monthnew.dto.AssetRptMonthNewListDTO;

/**
 * �űԼ�������Ȳ service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface AssetRptMonthNewListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param assetRptMonthNewListDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findConnList(AssetRptMonthNewListDTO assetRptMonthNewListDTO, User loginUser);    
}
