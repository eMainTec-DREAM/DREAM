package dream.asset.rpt.monthnew.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.monthnew.dto.AssetRptMonthNewListDTO;

/**
 * �űԼ�������Ȳ DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface AssetRptMonthNewListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param assetRptMonthNewListDTO
     * @return List
     */
    public List findConnList(AssetRptMonthNewListDTO assetRptMonthNewListDTO, User loginUser);
    
}