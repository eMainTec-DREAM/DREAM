package dream.asset.rpt.eqParts.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.rpt.eqParts.dto.AssetRptEqPartsDTO;

/**
 * 汲厚备己何前 - DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AssetRptEqPartsDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception;

    public String findTotalCount(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception;
}
