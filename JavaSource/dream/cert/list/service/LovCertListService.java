package dream.cert.list.service;

import java.util.List;

import common.bean.User;
import dream.cert.list.dto.LovCertListDTO;
import dream.cert.list.form.LovCertListForm;

/**
 * 자격증 Service
 * @author  hyosung
 * @version $Id: LovCertListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
 * @since   1.0
 *
 */
public interface LovCertListService
{

    /**
     * 자격증 검색
     * @author  hyosung
     * @version $Id: LovCertListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
     * @since   1.0
     * 
     * @param lovCertListDTO
     * @param loginUser
     * @param lovCertListForm
     * @return
     */
    List findCertList(LovCertListDTO lovCertListDTO, User loginUser, LovCertListForm lovCertListForm);
}