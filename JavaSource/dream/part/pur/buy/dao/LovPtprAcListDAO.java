package dream.part.pur.buy.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.org.emp.dto.LovEmpListDTO;
import dream.part.pur.buy.dto.LovPtprAcListDTO;

/**
 * 사원검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovPtprAcListDAO
{

    List findTaskMapAcList(LovPtprAcListDTO lovPtprAcListDTO, User user,
            Map<String, String> conditionMap);

    String findTotalCount(LovPtprAcListDTO lovPtprAcListDTO, User user,
            Map<String, String> conditionMap);

}