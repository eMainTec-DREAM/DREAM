package dream.part.pur.po.service;

import java.util.List;

import common.bean.User;
import dream.part.pur.po.dto.PartPurPoItemDTO;

/**
 * 발주품목- service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface PartPurPoItemService
{     
    public List findList(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception;
    public PartPurPoItemDTO findDetail(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception;
    public int insertDetail(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception;
    public int updateDetail(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception;
    public PartPurPoItemDTO getQty(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception;
}   
