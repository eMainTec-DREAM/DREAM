package dream.part.pur.buy.service;

import java.util.List;

import common.bean.User;
import dream.part.pur.buy.dto.LovPtprAcListDTO;
import dream.part.pur.buy.form.LovPtprAcListForm;

/**
 * »ç¿øÆË¾÷ Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovPtprAcListService
{

    List findTaskMapAcList(LovPtprAcListDTO lovPtprAcListDTO, User user, LovPtprAcListForm lovPtprAcListForm);

    String findTotalCount(LovPtprAcListDTO lovPtprAcListDTO, User user, LovPtprAcListForm lovPtprAcListForm);
    
}