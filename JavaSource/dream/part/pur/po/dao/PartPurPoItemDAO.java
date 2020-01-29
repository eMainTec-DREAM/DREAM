package dream.part.pur.po.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.pur.po.dto.PartPurPoItemDTO;

/**
 * 발주품목- dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface PartPurPoItemDAO extends BaseJdbcDaoSupportIntf
{
    public List find(PartPurPoItemDTO partPurPoItemDTO, User user);
    public int insert(PartPurPoItemDTO partPurPoItemDTO, User user);
    public int update(PartPurPoItemDTO partPurPoItemDTO, User user);
}