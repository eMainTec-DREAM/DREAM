package dream.asset.loc.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.loc.list.dto.LovMesLineListDTO;
import dream.asset.loc.list.form.LovMesLineListForm;

/**
 * MESLINE ÆË¾÷ Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovMesLineListService
{

    /**
     * MESLINE°Ë»ö
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovMesLineListDTO
     * @param loginUser
     * @return
     */
    List findMesLineList(LovMesLineListDTO lovMesLineListDTO, User loginUser);

    List findMesLineACList(LovMesLineListForm lovMesLineListForm, User user);
}