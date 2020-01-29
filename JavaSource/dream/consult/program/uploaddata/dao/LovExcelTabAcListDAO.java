package dream.consult.program.uploaddata.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.program.uploaddata.dto.LovExcelTabAcListDTO;

/**
 * Excel Tab ÆË¾÷
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface LovExcelTabAcListDAO
{
    /**
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovExcelTabAcListDTO
     * @param loginUser
     * @return
     */
    public List findExcelTabList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User loginUser);

    public List findExcelTabAcList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User user,
            Map<String, String> conditionMap);
}