package dream.pers.appstb.prc.dao;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * 예방점검 - 목록
 * @author  javaworker
 * @version $Id: AppPrcListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since   1.0
 */
public interface AppPrcListDAO
{
    public List findAppPrcList(AppReqCommonDTO appReqCommonDTO, User loginUser);
    

    public int deleteLine(String id, String compNo);
    
    public String findTotalCount(AppReqCommonDTO appReqCommonDTO, User loginUser)throws Exception;
}