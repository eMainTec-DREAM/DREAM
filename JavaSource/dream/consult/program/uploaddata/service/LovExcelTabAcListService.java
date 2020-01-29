package dream.consult.program.uploaddata.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.LovExcelTabAcListDTO;
import dream.consult.program.uploaddata.form.LovExcelTabAcListForm;

/**
 * Excel Tab Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovExcelTabAcListService
{

    /**
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovExcelTabAcListDTO
     * @param loginUser
     * @return
     */
    List findExcelTabList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User loginUser);

    List findExcelTabAcList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User user,
            LovExcelTabAcListForm lovExcelTabAcListForm);
}