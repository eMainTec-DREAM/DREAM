package dream.asset.rpt.eqParts.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.eqParts.dto.AssetRptEqPartsDTO;

/**
 * 汲厚备己何前 - Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface AssetRptEqPartsService
{
    public List findList(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception;
    
    public String findTotalCount(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception;
}
